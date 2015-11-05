
import java.sql.DriverManager
import java.sql.Connection



class jdbc {
  
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost/mydb"
  val username = "root"
  val password = "pass"
  var connection:Connection = null
  
  def connect() {
    try {
      Class.forName(driver)
      connection = DriverManager getConnection(url, username, password)
    } catch { case e : Throwable => e printStackTrace }
  }
  
  def disconnect(){
    connection close()
  }
  
  def getEmployee(){
    val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM employee")
      while ( resultSet next ) {
        val num = resultSet getInt("employeeid")
        val name = resultSet getString("name")
        val user = resultSet getString("username")
        val pass = resultSet getString("password")
        println("Product ID= " + num + "\nName: " + name + "\nUser: " + user + "\nPass: " + pass + "\n\n------------------\n")
      }
  }
 
}