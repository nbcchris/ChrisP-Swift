package com.qa.entitytests

import com.qa.entities.ProductOrder
import com.qa.testbase.TestBase

class ProductOrderTest extends TestBase{
   "A ProductOrder" should "be initialised with the correct values from the construtor" in {
    
    val pO = new ProductOrder(1, 2, "Test")
   
    pO.productOrderId.value should be (1)
    pO.employeeId.value should be (2)
    pO.status.value should be ("Test")
  }
   
   def testGetId {
     "getId" should "return an EmployeeId as an Integer" in{
       val cO = new ProductOrder(1,1,"Test")
       
       cO.getId should be (1)
     }
     it should "return the correct EmployeeId" in {
       val cO = new ProductOrder(1,5,"Test")
       
       cO.getId should be (5)
     }
   }
   
   testGetId
}