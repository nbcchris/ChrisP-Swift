package com.qa.databasetests

import com.qa.database.EmployeeSQL
import com.qa.testbase.TestBase

class EmployeeSQLTest extends TestBase{
  
  def testGetId{
    "getId" should "not return a null value" in{
      
      val employeeSQL = new EmployeeSQL
      
      employeeSQL getId("mouse") should not be (0)
    }
    it should "return the customer order with customer id 2 when called with the value '2'" in{
      
      val employeeSQL = new EmployeeSQL
      
      employeeSQL.getId("cliff") should be(3234)
    }
  }
  
  testGetId
}