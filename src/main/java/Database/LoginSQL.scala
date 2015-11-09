

package Database

import Entities.CustomerOrder
import scalafx.collections.ObservableBuffer
import java.sql.DriverManager
import java.sql.Connection

class LoginSQL {
  
  val db = new jdbc()
  
 def getLogin : Array[Array[String]] ={
    val connection : Connection = db connect()
    var list:Array[Array[String]]=Array(Array(null,null),Array(null,null),Array(null,null))
 
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM employee")
      var i = 0
      while ( resultSet next ) {
        list(i)(0)= resultSet getString("username")
        list(i)(1)= resultSet getString("password")
        i+=1
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    list
  }
}