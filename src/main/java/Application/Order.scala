

package Application

import Database.CustomerOrderSQL
import Entities.CustomerOrder
import Entities.Product
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
import scalafx.scene.control.ComboBox
import scalafx.geometry.Pos
import scalafx.collections.ObservableBuffer
import Database.ProductSQL

class Order (user : String, orderId_ : Int) extends JFXApp{
  
  
  def build : PrimaryStage={
    stage = new PrimaryStage {
      val dbc = new CustomerOrderSQL
      val dbp = new ProductSQL
      val order: CustomerOrder = dbc getOrder(orderId_)
      
      println(order.customerOrderId.value)
      title = "View Order"
      
      val combo = new ComboBox[Int]
      val options = dbc findLinesById(orderId_)
      combo.onAction = handle {
        val selected = combo.value.value
        val p: Product = dbp getProduct(selected)
        grid.children = Seq()
        grid.add(new Button("Return"){
          style = "-fx-font-size: 14pt"
          margin = Insets(80,0,0,130)
          onAction = handle{
              val a = new CustomerOrders(user)
              stage = a build
          }
        },2,6)
        grid.margin = Insets(0,100,100,100)
        
        grid.add(new Label("Product Name: "), 1,2)
        grid.add(new Label(p.name.value), 2,2)
        grid.add(new Label("Product Price: £"), 1,3)
        grid.add(new Label(p.price.value+"0"), 2,3)
          
        
      }
      
      combo.items = options
      combo.promptText="Choose a Product"
      
      val grid = new GridPane{
        style = "-fx-font-size: 14pt"
        
        add(new Button("Return"){
          style = "-fx-font-size: 14pt"
          margin = Insets(80,0,0,130)
          onAction = handle{
              val a = new CustomerOrders(user)
              stage = a build
          }
        },2,6)
        margin = Insets(0,100,100,100)
      }
      
      
      
      scene = new Scene{
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        content = new VBox{ 
          children = Seq(
            new GridPane{
              style = "-fx-font-size: 14pt"
              add(new Label("Order Id: "),1,1)
              add(new Label(""+order.customerOrderId.value),2,1)
              add(new Label("Employee: "),1,2)
              add(new Label(""+order.employeeId.value),2,2)
              add(new Label("Status: "),1,3)
              add(new Label(""+order.status.value),2,3)
              add(new Label("Products: "),1,4)
              add(combo,2,4)
              margin = Insets(80,100,10,100)
            },
            grid
          )
        }
      }
    }
    stage
  }
}