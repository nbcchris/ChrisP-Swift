

package Application

import Database.CustomerOrderSQL
import Entities.CustomerOrder
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color._
import scalafx.scene.paint.LinearGradient
import scalafx.beans.property.ObjectProperty
import scalafx.scene.paint.Stops
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.Includes._

class Order (orderId_ : Int) extends JFXApp{
  
  
  def build : PrimaryStage={
    stage = new PrimaryStage {
      val db = new CustomerOrderSQL
      val order: CustomerOrder = db getOrder(orderId_)
      
      println(order.customerOrderId.value)
      title = "View Order"
      
      scene = new Scene{
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        content = new VBox{ 
          children = Seq(
            new GridPane{
              style = "-fx-font-size: 24pt"
              add(new Label("Order Id: "),1,1)
              add(new Label(""+order.customerOrderId.value),2,1)
              add(new Label("Employee: "),1,2)
              add(new Label(""+order.employeeId.value),2,2)
              add(new Label("Status: "),1,3)
              add(new Label(""+order.status.value),2,3)
              margin = Insets(80,100,10,100)
            },
            new Button("Return"){
              style = "-fx-font-size: 14pt"
              margin = Insets(10,10,100,280)
              onAction = handle{
                  val a = new CustomerOrders
                  stage = a build
              }
            }
          )
        }
      }
    }
    stage
  }
}