

package Database

import scalafx.collections.ObservableBuffer
import java.sql.Connection
/**
 * @author ChrisPoole
 * 
 * 
 */
class EmployeeSQL {
  val db = new jdbc()
  
  def getId(user:String):Int={
    val connection : Connection = db connect()
    val oList:ObservableBuffer[Int]=ObservableBuffer[Int]()
    
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT employeeid FROM employee WHERE username ='"+user+"'")
      while ( resultSet next ) {
         oList += resultSet.getInt(1)
      }
      connection close
    } catch { case e : Throwable => e printStackTrace }
    
    oList(0)
  }
}