package TestPackunused

class Printer(name:String, rating: Float) {
  
  def this(name:String) = this(name, 0F)
  
    def print{
      println(name)
    }
  
  def getRating = this.rating
 }