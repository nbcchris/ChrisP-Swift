package com.qa.entitytests

import com.qa.entities.ProductOrderLine
import com.qa.testbase.TestBase

class ProductOrderLineineTest extends TestBase{
   "A ProductOrderLine" should "be initialised with the correct values from the construtor" in {
    
    val pOL = new ProductOrderLine(1, 2, 3)
   
    pOL.productId.value should be (1)
    pOL.productOrderId.value should be (2)
    pOL.quantity.value should be (3)
  }
}