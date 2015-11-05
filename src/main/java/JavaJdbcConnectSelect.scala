
import java.sql.DriverManager
import java.sql.Connection
 
/**
 * A Scala JDBC connection example by Alvin Alexander,
 * <a href="http://alvinalexander.com" title="http://alvinalexander.com">http://alvinalexander.com</a>
 */
object ScalaJdbcConnectSelect {
 
  def main(args: Array[String]) {
    // connect to the database named "mysql" on the localhost
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost/mydb"
    val username = "root"
    val password = "pass"
 
    // there's probably a better way to do this
    var connection:Connection = null
 
    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager getConnection(url, username, password)
 
      // create the statement, and run the select query
      val statement = connection createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM employee")
      while ( resultSet.next() ) {
        val num = resultSet getInt("employeeid")
        val name = resultSet getString("name")
        val user = resultSet getString("username")
        val pass = resultSet getString("password")
        println("Product ID= " + num + "\nName: " + name + "\nUser: " + user + "\nPass: " + pass + "\n\n------------------\n")
      }
    } catch {
      case e => e printStackTrace
    }
    connection close()
  }
 
}