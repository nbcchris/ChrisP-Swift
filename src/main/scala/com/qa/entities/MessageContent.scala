

package com.qa.entities

import java.io.Serializable

class MessageContent(obj : Object, str : String) extends Serializable{
  val contents : Object = obj
  val message : String = str
  
  def getContents() : Object = {
    contents
  }
  
  def getMessage() : String = {
    message
  }
}