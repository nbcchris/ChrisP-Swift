



import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.StringProperty

class Product (productId_ : Int, name_ : String, price_ : Float) {
  val productId = new ObjectProperty[Int](this, "productId", productId_)
  val name = new StringProperty(this, "name", name_)
  val price = new ObjectProperty[Float](this, "price", price_)  
}