
package Application

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableColumn.sfxTableColumn2jfx
import scalafx.scene.control.TableView
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color.SeaGreen
import scalafx.scene.paint.Color.PaleGreen
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.control.Button
import Entities.CustomerOrder
import Database.CustomerOrderSQL


/**
 * @author Crispy
 * 
 * Customer Orders is the page containing a list of all Customer Orders where Employees
 * are able to claim orders to work on in other areas of the system
 */
class CustomerOrders extends JFXApp {

  //method used to replace the PrimaryStage of the application and display new content
  //Returns a new PrimaryStage
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Order List"
      
      
      //Connect to the database
      val db = new CustomerOrderSQL()
      //Pull all information about CustomerOrders and store in 'order' buffer
      val order : ObservableBuffer[CustomerOrder] = db.getOrders
      
      scene = new Scene {
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        
        //Arrange content in a Horizontal Box
        content = new HBox {
          padding = Insets(3)
          children = Seq(
              new VBox{
                padding = Insets(0,20,0,20)
                children = Seq(
                  new Label{
                    text = "Customer Orders"
                    style = "-fx-font-size: 24pt"
                    effect = new DropShadow {
                      color = PaleGreen
                      radius = 25
                      spread = 0.25
                    }
                  },
                  new Button{
                    text = "View Order"
                    style = "-fx-font-size: 12pt"
                    padding=Insets(0,40,0,40)
                    margin=Insets(10,0,0,38)
                    onAction = handle{
                      System exit(0)
                    }
                  },
                  new Button{
                    text = "Claim"
                    style = "-fx-font-size: 12pt"
                    padding=Insets(0,40,0,40)
                    margin=Insets(10,0,0,58)
                    onAction = handle{
                      System exit(0)
                    }
                  },
                  new Label{
                    text = "Weclome ${USER}"
                    wrapText = true
                    style = "-fx-font-size: 10pt"
                    effect = new DropShadow {
                      color = PaleGreen
                      radius = 25
                      spread = 0.25
                    }
                  },
                  new Label{
                    text = "You currently have ${X} Orders claimed"
                    style = "-fx-font-size: 10pt"
                    effect = new DropShadow {
                      color = PaleGreen
                      radius = 25
                      spread = 0.25
                    }
                  }
                )
              },
              
              //Table Creation
              new TableView[CustomerOrder](order) {
              columns ++= List(
                new TableColumn[CustomerOrder, Int] {
                  text = "Order ID" 
                  cellValueFactory = { _.value.customerOrderId }
                  prefWidth = 100
                },
                new TableColumn[CustomerOrder, Int]() {
                  text = "Employee ID"
                  cellValueFactory = { _.value.employeeId }
                  prefWidth = 100
                },
                new TableColumn[CustomerOrder, Int] {
                  text = "Status"
                  cellValueFactory = { _.value.status }
                  prefWidth = 100
                  // Render the property value when it changes, 
                  // including initial assignment
                  }
              )
            }//Table finished
              
              
              
              
          )
        }
      }
    }
   stage
  }
}