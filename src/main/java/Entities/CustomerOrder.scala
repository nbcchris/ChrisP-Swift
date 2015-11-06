package Entities

import scalafx.scene.paint.Color
import scalafx.beans.property.ObjectProperty

class CustomerOrder(customerOrderId_ : Int, employeeId_ : Int, status_ : Color) {
  val customerOrderId = new ObjectProperty[Int](this, "orderId", customerOrderId_)
  val employeeId = new ObjectProperty[Int](this, "employeeId", employeeId_)
  val status = new ObjectProperty(this, "status", status_)
}