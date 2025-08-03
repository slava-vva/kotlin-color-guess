package com.example.colorguess


import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseSQLHelper {

    lateinit var conn: Connection
    private val ip =  "10.62.3.20" //"192.168.1.24"
    private val db = "KotlinDb"
    private val port = 1433
    private val username = "sa"
    private val password = "Password1!"

    fun dbConn():Connection?{
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        var connString: String? = null
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        }catch(ex: SQLException){
            Log.e("error: ",ex.message.toString())
        }catch(ex1:ClassNotFoundException){
            Log.e("error: ",ex1.message.toString())
        }catch(ex2:Throwable){
            Log.e("error: ",ex2.message.toString())
        }
        return conn
    }

}

//data class Car(val id: Int, val make: String, val model: String, val year: Int)

object CarsRepository {
    private val dbHelper = DatabaseSQLHelper()

    fun getAll(): List<Car> {
        val list = mutableListOf<Car>()
        val conn = dbHelper.dbConn()
        try {
            val stmt = conn?.createStatement()
            val rs = stmt?.executeQuery("SELECT * FROM Cars")
            while (rs != null && rs.next()) {
                list.add(
                    Car(
                        id = rs.getInt("Id"),
                        make = rs.getString("Make"),
                        model = rs.getString("Model"),
                        year = rs.getInt("Year")
                    )
                )
            }
            rs?.close()
            stmt?.close()
        } catch (e: Exception) {
            Log.e("DB_ERROR", e.toString())
        } finally {
            conn?.close()
        }
        return list
    }

    fun insert(car: Car) {
        val conn = dbHelper.dbConn()
        try {
            val ps = conn?.prepareStatement("INSERT INTO Cars (Make, Model, Year) VALUES (?, ?, ?)")
            ps?.setString(1, car.make)
            ps?.setString(2, car.model)
            ps?.setInt(3, car.year)
            ps?.executeUpdate()
            ps?.close()
        } catch (e: Exception) {
            Log.e("DB_ERROR", e.toString())
        } finally {
            conn?.close()
        }
    }

    fun update(car: Car) {
        val conn = dbHelper.dbConn()
        try {
            val ps = conn?.prepareStatement("UPDATE Cars SET Make = ?, Model = ?, Year = ? WHERE Id = ?")
            ps?.setString(1, car.make)
            ps?.setString(2, car.model)
            ps?.setInt(3, car.year)
            ps?.setInt(4, car.id)
            ps?.executeUpdate()
            ps?.close()
        } catch (e: Exception) {
            Log.e("DB_ERROR", e.toString())
        } finally {
            conn?.close()
        }
    }

    fun delete(id: Int) {
        val conn = dbHelper.dbConn()
        try {
            val ps = conn?.prepareStatement("DELETE FROM Cars WHERE Id = ?")
            ps?.setInt(1, id)
            ps?.executeUpdate()
            ps?.close()
        } catch (e: Exception) {
            Log.e("DB_ERROR", e.toString())
        } finally {
            conn?.close()
        }
    }
}
