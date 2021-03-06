



package com.qa.entities

import scalafx.beans.property.ObjectProperty
/**
 * @author ChrisPoole
 * 
 * 
 */
class ProductOrderLine(productId_ : Int, productOrderId_ : Int, quantity_ : Int) {
  val productOrderId = new ObjectProperty[Int](this, "productOrderId", productOrderId_)
  val productId = new ObjectProperty[Int](this, "productId", productId_)
  val quantity = new ObjectProperty[Int](this,"quantity", quantity_)  
}