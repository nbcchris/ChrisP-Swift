package Tests

import com.qa.database.CustomerOrderSQL
import com.qa.database.jdbc
import java.sql.ResultSet
import com.qa.entities.CustomerOrderLine
import scalafx.collections.ObservableBuffer
import com.qa.entities.CustomerOrder

class CustomerOrderSQLTest extends TestBase{
  
  def testGetOrder{
    
    "The getOrder " should "not return a null value" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      customerOrderSQL getOrder(1) should not be (null)
    }
    it should "return the customer order with customer id 2 when called with the value '2'" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      customerOrderSQL.getOrder(2).customerOrderId.value should be(2)
    }
    
  }
  
  def testGetOrders{
    "The getOrders" should "not return null" in{
     
     val customerOrderSQL = new CustomerOrderSQL
     
     val results = customerOrderSQL getOrders
     
     assert(results != null)
    }
    it should "return all the customer orders within the database" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      val db = new jdbc
      
      val resultSet = db getAnySQL("SELECT * FROM customerorder")
      
      val results = customerOrderSQL getOrders
      
      val customerOrderArray : ObservableBuffer[CustomerOrder] = ObservableBuffer[CustomerOrder]()
      
      def getData(rs : ResultSet, customerOrder : ObservableBuffer[CustomerOrder]) : Unit =
      {
        def getRSData()
        {
         if (rs next())
         {
          customerOrder += new CustomerOrder(rs.getInt(1), rs.getInt(2), rs.getString(3))
          getRSData
         }
        }
         getRSData
      }
      
      getData(resultSet, customerOrderArray)

      if(results.length == customerOrderArray.length ) assert(true) else assert(false)

     }
    it should  "return the correct data" in{
      val customerOrderSQL = new CustomerOrderSQL
      
      val db = new jdbc
      
      val resultSet = db getAnySQL("SELECT * FROM customerorder")
      
      val results = customerOrderSQL getOrders
      
      val customerOrderArray : ObservableBuffer[CustomerOrder] = ObservableBuffer[CustomerOrder]()
      
      def getData(rs : ResultSet, custOrder : ObservableBuffer[CustomerOrder]) : Unit =
      {
        def getRSData()
        {
         if (rs next())
         {
          custOrder += new CustomerOrder(rs.getInt(1), rs.getInt(2), rs.getString(3))
          getRSData
         }
        }
         getRSData
      }
      
      getData(resultSet, customerOrderArray)
      
      if(results(1).customerOrderId.value == customerOrderArray(1).customerOrderId.value
          && results(3).customerOrderId.value == customerOrderArray(3).customerOrderId.value)

        assert(true) else assert(false)
    }
  }
  
  def testFindLinesById{
    "The getLinesById method" should "not return null" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      val results = customerOrderSQL findLinesById(0)
      
      assert(results != null)
      
    }
    it should "return valid ProductIDs (Integers 1-20) within the database from a given Order ID" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      val results = customerOrderSQL findLinesById(1)
      
      (results(0)>0 && results(0)<20) should be(true)
    }
      
  }
  
  def testUpdateStatus{
    "The updateStatus" should "update the correct item within the table" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      customerOrderSQL.updateStatus(1, "TEST")
      
      customerOrderSQL.getOrder(1).status.value should be ("TEST")
    }
    it should "update the order status to Dispatched" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      customerOrderSQL.updateStatus(1, "Dispatched")
      
      customerOrderSQL.getOrder(1).status.value should be ("Dispatched")
    }
  }
  
  def testClaim{
    "Claim" should "update the correct item within the table" in{
      
      val customerOrderSQL = new CustomerOrderSQL
      
      customerOrderSQL claim(1, 1573)
      
      customerOrderSQL.getOrder(1).employeeId.value should be(1573)
      
    }
    it should "update the employeeId to 3234" in{
       val customerOrderSQL = new CustomerOrderSQL
      
      customerOrderSQL claim(5, 3234)
      
      customerOrderSQL.getOrder(5).employeeId.value should be(3234)
    }   
  }

  testGetOrder
  testGetOrders
  testFindLinesById
  testUpdateStatus
  testClaim

}