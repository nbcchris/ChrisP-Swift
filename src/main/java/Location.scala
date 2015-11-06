



import scalafx.beans.property.ObjectProperty

class Location (locationId_ : Int, quantity_ : Int, productId_ : Int) {
  val employeeId = new ObjectProperty[Int](this, "locationId", locationId_)
  val name = new ObjectProperty[Int](this, "quantity", quantity_)
  val username = new ObjectProperty[Int](this, "productId", productId_)  
}