
package com.qa.database

import java.sql.DriverManager
import java.sql.Connection
import scalafx.collections.ObservableBuffer
import com.qa.entities.CustomerOrder
import java.sql.ResultSet
import java.sql.SQLException

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
  
  def getAnySQL(query : String) : ResultSet = 
  {
    try{
      
      val connection : Connection = connect
      
      val statement = connection createStatement
      val resultSet = statement executeQuery(query)

      resultSet

    }
    catch 
    {
      case e : SQLException => println("SQL Exception")
      null
    }
  }
  
}