object Console {
  def main(args : Array[String]){
    val db = new jdbc()

    println(db getLogin(0))

  }
}