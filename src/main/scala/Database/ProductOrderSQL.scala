

package Database
/**
 * @author ChrisPoole
 * 
 * 
 */
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
        oList += new ProductOrder(resultSet.getInt(1),resultSet.getInt(4), resultSet.getString(3))
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    oList
  }
  
  def getOrder(orderId: Int) : ProductOrder = {
    val connection : Connection = db connect()
    val oList:ObservableBuffer[ProductOrder]=ObservableBuffer[ProductOrder]()
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT productorderid, employee_employeeid, status FROM productorder WHERE productorderid ="+orderId)
      while ( resultSet next ) {
         oList += new ProductOrder(resultSet.getInt(1),resultSet.getInt(2), resultSet.getString(3))
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    oList(0)
  }
  
  def findLinesById(orderId : Int) : ObservableBuffer[Int]={
    val connection : Connection = db connect()
    val oList:ObservableBuffer[Int]=ObservableBuffer[Int]()
    
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT product_productid FROM productorderline WHERE productorder_productorderid ="+orderId)
      while ( resultSet next ) {
         oList += resultSet.getInt(1)
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    
    oList
  }
  
  def claim(orderId: Int, userid: Int){
    val connection : Connection = db connect()
    val oList:ObservableBuffer[Int]=ObservableBuffer[Int]()
    
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeUpdate("UPDATE productorder SET employee_employeeid = '"+userid+"' WHERE `productorderid`="+orderId)
      connection close()
    } catch { case e : Throwable => e printStackTrace }
  }
  
  def updateStatus(orderId : Int, status : String){
    val connection : Connection = db connect()
    val oList:ObservableBuffer[Int]=ObservableBuffer[Int]()
    
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeUpdate("UPDATE productorder SET status = '"+status+"' WHERE `productorderid`="+orderId)
      connection close()
    } catch { case e : Throwable => e printStackTrace }
  }
}