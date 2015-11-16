

package com.qa.database

import com.qa.entities.CustomerOrder
import scalafx.collections.ObservableBuffer
import java.sql.DriverManager
import java.sql.Connection
/**
 * @author ChrisPoole
 * 
 * 
 */
class LoginSQL {
  
  val db = new jdbc()

  def getPass(user : String):String={
    val connection:Connection=db connect()
    val oList:ObservableBuffer[String]=ObservableBuffer[String]()
    
    try{
      val statement = connection.createStatement()
      val resultSet = statement executeQuery("SELECT password FROM employee WHERE username ='"+user+"'")
      
      def getResults(){
       if (resultSet next()){
         oList+= resultSet.getString(1)
         getResults
        }
      }
      
      getResults
      connection close
    }catch{
      case e : Throwable => e printStackTrace 
    }
    oList(0)
  }
}