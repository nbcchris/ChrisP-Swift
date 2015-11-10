

package Database

import Entities.Product
import scalafx.collections.ObservableBuffer
import java.sql.Connection
/**
 * @author ChrisPoole
 * 
 * 
 */
class ProductSQL {
  
  val db = new jdbc()
  
  def getProduct(productId: Int) : Product = {
    val connection : Connection = db connect()
    val p:ObservableBuffer[Product]=ObservableBuffer[Product]()
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT productid, name, price FROM product WHERE productid ="+productId)
      while ( resultSet next ) {
         p += new Product(resultSet.getInt(1),resultSet.getString(2), resultSet.getFloat(3))
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    p(0)
  }
}