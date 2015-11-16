package com.qa.entitytests

import com.qa.entities.Location
import com.qa.testbase.TestBase

class LocationTest extends TestBase{
   "A Location" should "be initialised with the correct values from the construtor" in {
    
    val l = new Location(1, 2, 3)
   
    l.locationId.value should be (1)
    l.quantity.value should be (2)
    l.productId.value should be (3)
  }
}