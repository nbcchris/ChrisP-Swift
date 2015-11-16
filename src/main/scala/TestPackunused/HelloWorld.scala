package TestPackunused

object HelloWorld {
  
  def loop(callback: () => Unit){
    callback(); Thread sleep 1000
    loop(callback)
  }
  
  def printme(){
    val msg = "Beep"
    val x = true
    if(msg == "Beep" ^ !x){
      val p = new Printer(msg, 0F)
      p getRating match {
        case 1F => print("Good ")
        case 2F => print("Bad ")
        case 3F => print("Ugly ")
        case _ => print("Invalid ")
      }
      p print
    }else println("nope")
  }
  
  def main(args: Array[String]){
    val m = new Minesweeper(30,30,300)
    m drawGrid(m generateGrid,0,0)
    
    val daisy = new Dalmatian()
    daisy grow
  }
  
  
  val filter = (xs : List[Int]) => {
    val order = (x: Int) => x%2==0
    for(x <- xs; if(order(x))) yield x
  }
}

