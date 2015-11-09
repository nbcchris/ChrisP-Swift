



package Entities

import scalafx.beans.property.ObjectProperty

class ProductOrder(productOrderId_ : Int,status_ : Int,employeeId_ : Int) {
  val productOrderId = new ObjectProperty[Int](this, "productOrderId", productOrderId_)
  val status = new ObjectProperty[Int](this, "status", status_)
  val employeeId = new ObjectProperty[Int](this,"employeeId", employeeId_)  
}