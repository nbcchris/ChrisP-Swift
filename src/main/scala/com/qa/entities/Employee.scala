

package com.qa.entities

import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.StringProperty

/**
 * @author ChrisPoole
 * 
 * 
 */
class Employee (employeeId_ : Int, name_ : String, username_ : String, password_ : String) {
  val employeeId = new ObjectProperty[Int](this, "employeeId", employeeId_)
  val name = new StringProperty(this, "name", name_)
  val username = new StringProperty(this, "username", username_)
  val password = new StringProperty(this, "password", password_)  
}