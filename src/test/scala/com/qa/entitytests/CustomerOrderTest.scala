package com.qa.entitytests

import com.qa.entities.CustomerOrder
import com.qa.testbase.TestBase

class CustomerOrderTest extends TestBase{
   "A CustomerOrder" should "be initialised with the correct values from the construtor" in {
    
    val cO = new CustomerOrder(1, 2, "Test")
   
    cO.customerOrderId.value should be (1)
    cO.employeeId.value should be (2)
    cO.status.value should be ("Test")
  }
   
   def testGetId {
     "getId" should "return an EmployeeId as an Integer" in{
       val cO = new CustomerOrder(1,1,"Test")
       
       cO.getId should be (1)
     }
     it should "return the correct EmployeeId" in {
       val cO = new CustomerOrder(1,5,"Test")
       
       cO.getId should be (5)
     }
   }
   
   testGetId
}