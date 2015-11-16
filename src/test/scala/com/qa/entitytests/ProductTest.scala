package com.qa.entitytests

import com.qa.entities.Product
import com.qa.testbase.TestBase

class ProductTest extends TestBase{
   "A Product" should "be initialised with the correct values from the construtor" in {
    
    val p = new Product(1, "Test", 3.65F)
   
    p.productId.value should be (1)
    p.name.value should be ("Test")
    p.price.value should be (3.65F)
  }
}