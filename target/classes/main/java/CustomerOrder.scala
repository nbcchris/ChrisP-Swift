

import scalafx.scene.paint.Color
import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.StringProperty

class CustomerOrder(customerOrderId_ : Int, employeeId_ : Int, status_ : Int) {
  val customerOrderId = new ObjectProperty[Int](this, "orderId", customerOrderId_)
  val employeeId = new ObjectProperty[Int](this, "employeeId", employeeId_)
  val status = new ObjectProperty[Int](this, "status", status_)
}