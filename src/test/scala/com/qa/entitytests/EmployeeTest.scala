package com.qa.entitytests

import com.qa.testbase.TestBase
import com.qa.entities.Employee

class EmployeeTest extends TestBase{
   "An Employee" should "be initialised with the correct values from the construtor" in {
    
    val e = new Employee(1, "Test", "Test2", "Test3")
   
    e.employeeId.value should be (1)
    e.name.value should be ("Test")
    e.username.value should be ("Test2")
    e.password.value should be ("Test3")
  }
}