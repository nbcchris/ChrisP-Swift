object Console {
  def main(args : Array[String]){
    val db = new jdbc()
    db connect()
    db getEmployee()
    db disconnect()
  }
}