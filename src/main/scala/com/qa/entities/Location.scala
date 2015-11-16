



package com.qa.entities

import scalafx.beans.property.ObjectProperty
/**
 * @author ChrisPoole
 * 
 * 
 */
class Location (locationId_ : Int, quantity_ : Int, productId_ : Int) {
  val locationId = new ObjectProperty[Int](this, "locationId", locationId_)
  val quantity = new ObjectProperty[Int](this, "quantity", quantity_)
  val productId = new ObjectProperty[Int](this, "productId", productId_)  
}