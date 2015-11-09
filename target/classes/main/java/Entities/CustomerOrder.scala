

package Entities

import scalafx.beans.property.ObjectProperty
/**
 * @author ChrisPoole
 * 
 * 
 */
class CustomerOrder(customerOrderId_ : Int, employeeId_ : Int, status_ : Int) {
  val customerOrderId = new ObjectProperty[Int](this, "orderId", customerOrderId_)
  val employeeId = new ObjectProperty[Int](this, "employeeId", employeeId_)
  val status = new ObjectProperty[Int](this, "status", status_)
}