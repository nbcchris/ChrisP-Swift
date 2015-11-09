

package Database

import Entities.ProductOrder
import scalafx.collections.ObservableBuffer
import java.sql.Connection

class ProductOrderSQL {
    
  val db = new jdbc()
  
 def getOrders : ObservableBuffer[ProductOrder]={
    val connection : Connection = db connect()
    val oList:ObservableBuffer[ProductOrder]=ObservableBuffer[ProductOrder]()
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM productorder")
      while ( resultSet next ) {
        oList += new ProductOrder(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3))
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    oList
  }
}