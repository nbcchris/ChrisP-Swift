
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
  
}