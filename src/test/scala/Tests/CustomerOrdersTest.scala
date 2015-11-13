package Tests

import Application.CustomerOrders

class CustomerOrdersTest extends TestBase {
  
  val tester = new CustomerOrders("")
  
  tester build
  
  def testBuild() {
    "Build" should "return a new Stage" in{
      println("OUTPUT: "+tester.stage)//.getClass.getSimpleName)
      //assert(tester.build.equals(tester.build))
    }
  }
  testBuild
}