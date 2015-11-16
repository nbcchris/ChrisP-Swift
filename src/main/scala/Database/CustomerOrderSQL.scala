

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
      
      def getResults(){
       if (resultSet next()){
         oList += new CustomerOrder(resultSet.getInt(1),resultSet.getInt(2), resultSet.getString(3))
         getResults
        }
      }
      
      getResults
      
    } catch { case e : Throwable => e printStackTrace }
    oList
  }
  
  def getOrder(orderId: Int) : CustomerOrder = {
    val connection : Connection = db connect()
    val oList:ObservableBuffer[CustomerOrder]=ObservableBuffer[CustomerOrder]()
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT customerorderid, employee_employeeid, status FROM customerorder WHERE customerorderid ="+orderId)
      
      def getResults(){
       if (resultSet next()){
         oList += new CustomerOrder(resultSet.getInt(1),resultSet.getInt(2), resultSet.getString(3))
         getResults
        }
      }
      
      getResults
     
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    oList(0)
  }
  
  def findLinesById(orderId : Int) : ObservableBuffer[Int]={
    val connection : Connection = db connect()
    val oList:ObservableBuffer[Int]=ObservableBuffer[Int]()
    
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT product_productid FROM customerorderline WHERE customerorder_customerorderid ="+orderId)
      def getResults(){
       if (resultSet next()){
         oList += resultSet.getInt(1)
         getResults
        }
      }
      
      getResults
     
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    
    oList
  }
  
  def updateStatus(orderId : Int, status : String){
    val connection : Connection = db connect()
    val oList:ObservableBuffer[Int]=ObservableBuffer[Int]()
    
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeUpdate("UPDATE customerorder SET status = '"+status+"' WHERE `customerorderid`="+orderId)
      connection close()
    } catch { case e : Throwable => e printStackTrace }
  }
  
  def claim(orderId: Int, userid: Int){
    val connection : Connection = db connect()
    val oList:ObservableBuffer[Int]=ObservableBuffer[Int]()
    
    try {
      val statement = connection createStatement()
      val resultSet = statement.executeUpdate("UPDATE customerorder SET employee_employeeid = '"+userid+"' WHERE `customerorderid`="+orderId)
      connection close()
    } catch { case e : Throwable => e printStackTrace }
  }
}