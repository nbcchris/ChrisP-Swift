



package Entities

import scalafx.beans.property.ObjectProperty
/**
 * @author ChrisPoole
 * 
 * 
 */
class CustomerOrderLine (customerOrderId_ : Int,  productId_ : Int, quantity_ : Int) {
  val customerOrderId = new ObjectProperty[Int](this, "orderId", customerOrderId_)
  val productId = new ObjectProperty[Int](this, "productId", productId_)
  val quantity = new ObjectProperty(this, "quantity", quantity_)
}