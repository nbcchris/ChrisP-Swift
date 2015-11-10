

package Entities

import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.StringProperty
/**
 * @author ChrisPoole
 * 
 * 
 */
class CustomerOrder(customerOrderId_ : Int, employeeId_ : Int, status_ : String) {
  val customerOrderId = new ObjectProperty[Int](this, "orderId", customerOrderId_)
  val employeeId = new ObjectProperty[Int](this, "employeeId", employeeId_)
  val status = new StringProperty(this, "status", status_)
}