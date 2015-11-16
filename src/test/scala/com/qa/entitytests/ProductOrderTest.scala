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
}