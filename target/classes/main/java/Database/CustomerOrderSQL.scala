

package Database

import Entities.CustomerOrder
import scalafx.collections.ObservableBuffer
import java.sql.DriverManager
import java.sql.Connection

class CustomerOrderSQL {
  
  val db = new jdbc()
  
 def getOrders : ObservableBuffer[CustomerOrder]={
    val connection : Connection = db connect()
    val oList:ObservableBuffer[CustomerOrder]=ObservableBuffer[CustomerOrder]()
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM customerorder")
      while ( resultSet next ) {
        oList += new CustomerOrder(resultSet.getInt(1),resultSet.getInt(3), resultSet.getInt(4))
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    oList
  }
}