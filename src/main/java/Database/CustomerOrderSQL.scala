

package Database

import Entities.CustomerOrder
import scalafx.collections.ObservableBuffer
import java.sql.DriverManager
import java.sql.Connection
/**
 * @author ChrisPoole
 * 
 * 
 */
class CustomerOrderSQL {
  
  val db = new jdbc()
  
 def getOrders : ObservableBuffer[CustomerOrder]={
    val connection : Connection = db connect()
    val oList:ObservableBuffer[CustomerOrder]=ObservableBuffer[CustomerOrder]()
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT customerorderid, employee_employeeid, status FROM customerorder")
      while ( resultSet next ) {
        oList += new CustomerOrder(resultSet.getInt(1),resultSet.getInt(2), resultSet.getString(3))
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    oList
  }
  
  def getOrder(orderId: Int) : CustomerOrder = {
    val connection : Connection = db connect()
    val oList:ObservableBuffer[CustomerOrder]=ObservableBuffer[CustomerOrder]()
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT customerorderid, employee_employeeid, status FROM customerorder WHERE customerorderid ="+orderId)
      while ( resultSet next ) {
         oList += new CustomerOrder(resultSet.getInt(1),resultSet.getInt(2), resultSet.getString(3))
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    oList(0)
  }
}