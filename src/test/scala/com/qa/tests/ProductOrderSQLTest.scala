package com.qa.tests

import com.qa.database.ProductOrderSQL
import com.qa.database.jdbc
import java.sql.ResultSet
import com.qa.entities.ProductOrderLine
import scalafx.collections.ObservableBuffer
import com.qa.entities.ProductOrder

class ProductOrderSQLTest extends TestBase{
  
  def testGetOrder{
    
    "The getOrder " should "not return a null value" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      productOrderSQL getOrder(1) should not be (null)
    }
    it should "return the Product order with Product id 2 when called with the value '2'" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      productOrderSQL.getOrder(2).productOrderId.value should be(2)
    }
    
  }
  
  def testGetOrders{
    "The getOrders" should "not return null" in{
     
     val productOrderSQL = new ProductOrderSQL
     
     val results = productOrderSQL getOrders
     
     assert(results != null)
    }
    it should "return all the Product orders within the database" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      val db = new jdbc
      
      val resultSet = db getAnySQL("SELECT * FROM productorder")
      
      val results = productOrderSQL getOrders
      
      val productOrderArray : ObservableBuffer[ProductOrder] = ObservableBuffer[ProductOrder]()
      
      def getData(rs : ResultSet, productOrder : ObservableBuffer[ProductOrder]) : Unit =
      {
        def getRSData()
        {
         if (rs next())
         {
          productOrder += new ProductOrder(rs.getInt(1), rs.getInt(2), rs.getString(3))
          getRSData
         }
        }
         getRSData
      }
      
      getData(resultSet, productOrderArray)

      if(results.length == productOrderArray.length ) assert(true) else assert(false)

     }
    it should  "return the correct data" in{
      val productOrderSQL = new ProductOrderSQL
      
      val db = new jdbc
      
      val resultSet = db getAnySQL("SELECT * FROM productorder")
      
      val results = productOrderSQL getOrders
      
      val ProductOrderArray : ObservableBuffer[ProductOrder] = ObservableBuffer[ProductOrder]()
      
      def getData(rs : ResultSet, productOrder : ObservableBuffer[ProductOrder]) : Unit =
      {
        def getRSData()
        {
         if (rs next())
         {
          productOrder += new ProductOrder(rs.getInt(1), rs.getInt(2), rs.getString(3))
          getRSData
         }
        }
         getRSData
      }
      
      getData(resultSet, ProductOrderArray)
      
      if(results(1).productOrderId.value == ProductOrderArray(1).productOrderId.value
          && results(3).productOrderId.value == ProductOrderArray(3).productOrderId.value)

        assert(true) else assert(false)
    }
  }
  
  def testFindLinesById{
    "The getLinesById method" should "not return null" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      val results = productOrderSQL findLinesById(0)
      
      assert(results != null)
      
    }
    it should "return valid ProductIDs (Integers 1-20) within the database from a given Order ID" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      val results = productOrderSQL findLinesById(1)
      
      (results(0)>0 && results(0)<20) should be(true)
    }
      
  }
  
  def testUpdateStatus{
    "The updateStatus" should "update the correct item within the table" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      productOrderSQL.updateStatus(1, "TEST")
      
      productOrderSQL.getOrder(1).status.value should be ("TEST")
    }
    it should "update the order status to Dispatched" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      productOrderSQL.updateStatus(1, "Dispatched")
      
      productOrderSQL.getOrder(1).status.value should be ("Dispatched")
    }
  }
  
  def testClaim{
    "Claim" should "update the correct item within the table" in{
      
      val productOrderSQL = new ProductOrderSQL
      
      productOrderSQL claim(1, 1573)
      
      productOrderSQL.getOrder(1).employeeId.value should be(1573)
      
    }
    it should "update the employeeID to 3234" in{
       val ProductOrderSQL = new ProductOrderSQL
      
      ProductOrderSQL claim(5, 3234)
      
      ProductOrderSQL.getOrder(5).employeeId.value should be(3234)
    }   
  }
  
  testGetOrder
  testGetOrders
  testFindLinesById
  testUpdateStatus
  testClaim
  
}