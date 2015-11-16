



package com.qa.entities

import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.StringProperty
/**
 * @author ChrisPoole
 * 
 * 
 */
class ProductOrder(productOrderId_ : Int,employeeId_ : Int,status_ : String) {
  val productOrderId = new ObjectProperty[Int](this, "productOrderId", productOrderId_)
  val status = new StringProperty(this, "status", status_)
  val employeeId = new ObjectProperty[Int](this,"employeeId", employeeId_)  
}