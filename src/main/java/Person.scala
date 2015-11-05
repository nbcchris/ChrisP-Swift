import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.StringProperty
import scalafx.scene.paint.Color

class Person(orderId_ : Int,  employeeId_ : Int, status_ : Color) {
  val orderId = new ObjectProperty[Int](this, "orderId", orderId_)
  val employeeId = new ObjectProperty[Int](this, "employeeId", employeeId_)
  val status = new ObjectProperty(this, "status", status_)
}