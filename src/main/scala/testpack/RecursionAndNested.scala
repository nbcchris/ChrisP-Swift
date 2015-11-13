

package testpack

object RecursionAndNested {
  def filter(l1:List[Int], limit:Int){
    def process(l2:List[Int]):List[Int] ={
      if(l2.isEmpty) l2
      else if (l2.head < limit)
        l2.head :: process(l2.tail)
      else
        process(l2.tail)
    }
    println(process(l1))
  }
  
  def main(args : Array[String]){
    filter(List(1,9,2,8,3,7,4),5)
  }
}