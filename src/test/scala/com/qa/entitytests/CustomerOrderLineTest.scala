package com.qa.entitytests

import com.qa.entities.CustomerOrderLine
import com.qa.testbase.TestBase

class CustomerOrderLineTest extends TestBase{
   "A CustomerOrderLine" should "be initialised with the correct values from the construtor" in {
    
    val cOL = new CustomerOrderLine(1, 2, 3)
   
    cOL.customerOrderId.value should be (1)
    cOL.productId.value should be (2)
    cOL.quantity.value should be (3)
  }
}