

package TestPackunused

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

object Threading {
  
  val generateXGrid = Future {
      val grid: Minesweeper = new Minesweeper(2,2,3)
      val board = grid.drawGrid(grid generateGrid, 2, 2)
      board
    }
  
  def main(args: Array[String]){
    time onComplete {
      case Success(_) => {
        val grid = Await.result(generateXGrid, 100 nano)
        println("Success")
      }
        
      case Failure(_) => println("Error generating grid")
    }
    
  }
  
  def time = Future { Thread.sleep(10) }
}