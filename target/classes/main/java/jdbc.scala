
import java.sql.DriverManager
import java.sql.Connection


class jdbc {
  
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost/mydb"
  val username = "root"
  val password = "pass"
  var connection:Connection = null
  var list:Array[Array[String]]//=Array(Array(null,null),Array(null,null),Array(null,null))
  var pList:Array[Product]
  
  def getEmployee {
    try {
      Class.forName(driver)
      connection = DriverManager getConnection(url, username, password)
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM employee")
      while ( resultSet next ) {
        val num = resultSet getInt("employeeid")
        val name = resultSet getString("name")
        val user = resultSet getString("username")
        val pass = resultSet getString("password")
        println("Employee ID= " + num + "\nName: " + name + "\nUser: " + user + "\nPass: " + pass + "\n\n------------------\n")
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
  }

  def getLogin : Array[Array[String]] ={
    try {
      Class.forName(driver)
      connection = DriverManager getConnection(url, username, password)
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM employee")
      var i = 0
      while ( resultSet next ) {
        list(i)(0)= resultSet getString("username")
        list(i)(1)= resultSet getString("password")
        i+=1
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    list
  }
  
  def makeProduct : Array[Product]={
    try {
      Class.forName(driver)
      connection = DriverManager getConnection(url, username, password)
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM product")
      var i = 0
      while ( resultSet next ) {
        val x = resultSet getInt("productid")
        val y = resultSet getString("name")
        val z = resultSet getFloat("price")
        pList(i) = new Product(x,y,z)
      }
      connection close()
    } catch { case e : Throwable => e printStackTrace }
    pList
  }
}