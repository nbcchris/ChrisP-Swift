
package Database

import java.sql.DriverManager
import java.sql.Connection
import scalafx.collections.ObservableBuffer
import Entities.CustomerOrder

/**
 * @author ChrisPoole
 * 
 * 
 */
class jdbc {
  var connection:Connection = null
  
  
  def connect() : Connection = {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost/mydb"
  val username = "root"
  val password = "pass"
  
  try{
  Class.forName(driver)
  connection = DriverManager getConnection(url, username, password)
      } catch { case e : Throwable => e printStackTrace }
      connection
  }
  
  
  /*
  def getEmployee {
    try {
      Class.forName(driver)
      connection = DriverManager getConnection(url, username, password)
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM employee")
      while ( resultSet next ) {
        val num = resultSet getInt("employeeid")
        val name = resultSet getString("name")
        val user = resultSet getString("username")
        val pass = resultSet getString("password")
        println("Employee ID= " + num + "\nName: " + name + "\nUser: " + user + "\nPass: " + pass + "\n\n------------------\n")
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
  }

  
  */
  
}