package testpack

import scala.util.Random

class Minesweeper(x: Int, y: Int, mines: Int) {
  def generateGrid: Array[Array[Int]]={
    val grid: Array[Array[Int]] = Array.ofDim[Int](x,y)
    def placeMines(mineLocations: Array[Array[Int]],placed:Int): Array[Array[Int]] ={
        if(placed==mines)mineLocations
        else{
          val xcoord = Random.nextInt(x)
          val ycoord = Random.nextInt(y)
          if(mineLocations(xcoord)(ycoord) == 9) placeMines(mineLocations, placed) 
          else{
             mineLocations(xcoord)(ycoord) = 9
             placeMines(mineLocations, placed.+(1))
          }
        }
      }
    placeMines(grid,0)
  }
  
  def drawGrid(grid:Array[Array[Int]], x1:Int, y1:Int){
    val d = grid(x1)(y1)
    if(d==9)print("[@]")
    else print("[ ]")
    if(x1<grid.size-1) drawGrid(grid,x1.+(1),y1)
    else if(y1==grid.size){  }
    else if(y1<grid.size-1){
      print("\n")
      drawGrid(grid,x1=0,y1.+(1))
    }
  }
}