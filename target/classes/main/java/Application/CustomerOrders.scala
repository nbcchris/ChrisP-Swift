
package Application

import Database.CustomerOrderSQL
import Entities.CustomerOrder
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Rectangle
import scalafx.Includes.handle
import scalafx.Includes.jfxRectangle2sfx
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.geometry.Orientation
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.control.ComboBox
import scalafx.scene.control.Separator
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableColumn.sfxTableColumn2jfx
import scalafx.scene.control.TableView
import scalafx.scene.control.ToggleGroup
import scalafx.scene.control.ToolBar
import scalafx.scene.control.Tooltip
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color.PaleGreen
import scalafx.scene.paint.Color.SeaGreen
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops


/**
 * @author ChrisPoole
 * 
 * Customer Orders is the page containing a list of all Customer Orders where Employees
 * are able to claim orders to work on in other areas of the system
 */
class CustomerOrders extends JFXApp {

  //method used to replace the PrimaryStage of the application and display new content
  //Returns a new PrimaryStage
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Customer Orders"
      
      
      //Connect to the database
      val db = new CustomerOrderSQL()
      //Pull all information about CustomerOrders and store in 'order' buffer
      val order : ObservableBuffer[CustomerOrder] = db.getOrders
      
      def createRect(): Rectangle ={
        val image = new Image("file:src/images/logo.png")
        val rect = new Rectangle(0,0,80,80)
        rect setFill(new ImagePattern(image))
        rect
      }
      
      val combo : ComboBox[String] = new ComboBox()
      val options : ObservableBuffer[String] = ObservableBuffer[String]()
        options += "Picked"
        options += "Packed"
        options += "Dispatched"
        options += "Complete"
        
      combo.items = options
      
      val alignToggleGroup = new ToggleGroup()
      val toolBar = new ToolBar {
        content = List(
          new Button {
            id = "newButton"
            graphic = createRect
            tooltip = Tooltip("New Document... Ctrl+N")
            onAction = handle {
              val a = new Index
              stage = a build
            }
          },
          new Button {
            id = "viewOrder"
            text = "View Order"
          },
          new Button {
            id = "claim"
            text = "Claim Order"
          },
          new Separator {
            orientation = Orientation.VERTICAL
          },
          combo,
          new Button {
            id = "changeStatus"
            text = "Change Status"
          }
        )
      }
      
      
      scene = new Scene {
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
       
        
        println(options)
        
        //Arrange content in a Horizontal Box
        content = new VBox{ 
          padding = Insets(3)
          children = Seq(
            toolBar,
            new  HBox {
              children = Seq(
                //Table Creation
                new TableView[CustomerOrder](order) {
                  columns ++= List(
                    new TableColumn[CustomerOrder, Int] {
                    text = "Order ID" 
                    cellValueFactory = { _.value.customerOrderId }
                    prefWidth = 163
                    },
                  new TableColumn[CustomerOrder, Int]() {
                    text = "Employee ID"
                    cellValueFactory = { _.value.employeeId }
                    prefWidth = 163
                    },
                  new TableColumn[CustomerOrder, Int] {
                    text = "Status"
                    cellValueFactory = { _.value.status }
                    prefWidth = 163
                    // Render the property value when it changes, 
                    // including initial assignment
                    }
                  )
                }//Table finished
              )
            }
          )
        }
      }  
    }
   stage
  }
}