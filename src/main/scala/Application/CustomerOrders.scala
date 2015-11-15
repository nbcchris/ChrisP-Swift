
package Application

import Database.CustomerOrderSQL
import Database.EmployeeSQL
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
import scalafx.event.ActionEvent


/**
 * @author ChrisPoole
 * 
 * Customer Orders is the page containing a list of all Customer Orders where Employees
 * are able to claim orders to work on in other areas of the system
 */
class CustomerOrders(user : String) extends JFXApp {
  
  val empdb = new EmployeeSQL()
  val db = new CustomerOrderSQL()
  
  def createRect(): Rectangle ={
        val image = new Image("file:src/images/logo.png")
        val rect = new Rectangle(0,0,80,80)
        rect setFill(new ImagePattern(image))
        rect
      }
  //method used to replace the PrimaryStage of the application and display new content
  //Returns a new PrimaryStage
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Customer Orders"
      resizable = false
      var coid = 0
      var filtered = false
      //Connect to the database
      val db = new CustomerOrderSQL()
      val empdb = new EmployeeSQL()
      val order : ObservableBuffer[CustomerOrder] = db.getOrders
      //Pull all information about CustomerOrders and store in 'order' buffer

      def filter(order : ObservableBuffer[CustomerOrder]): ObservableBuffer[CustomerOrder] = {
        val orders = (x : CustomerOrder) =>  x.getId % empdb.getId(user) == 0
        for(x <- order; if(orders(x))) yield x
      }

      val table = if(filtered) buildTable(filter(order)) else buildTable(order)
        
      table.onMouseClicked = handle {
          try{
            coid = table.getSelectionModel.selectedItemProperty.get.customerOrderId.value
          }catch{
            case e : Throwable => e printStackTrace
          }
        }
      //creates image bound
      
      
      //Create Combobox and populate
      val combo : ComboBox[String] = new ComboBox()
      val options : ObservableBuffer[String] = ObservableBuffer[String]()
        options += "Picked"
        options += "Packed"
        options += "Dispatched"
        options += "Complete"
      combo.promptText = "Pick a Status"
      combo.items = options
      
      //Create Toolbar
      val toolBar = new ToolBar {
        content = List(
          new Button {
            id = "newButton"
            graphic = createRect
            tooltip = Tooltip("Back to Index")
            onAction = handle {
              val a = new Index(user)
              stage = a build
            }
          },
          new Button {
            id = "viewOrder"
            text = "View Order"
            onMouseClicked = handle{
              val a = new Order(user, coid)
              stage = a build
            }
          },
          new Button {
            id = "claim"
            text = "Claim Order"
            onMouseClicked = handle {
              val userid = empdb getId(user)
              db claim(coid, userid)
              val a = new CustomerOrders(user)
              stage = a build
            }
          },
          new Separator {
            orientation = Orientation.VERTICAL
          },
          combo,
          new Button {
            id = "changeStatus"
            text = "Change Status"
            onMouseClicked = handle {
              val selected = combo.value.value
              if(selected != null){
                db updateStatus(coid,selected)
                val a = new CustomerOrders(user)
                stage = a build
              }
            }
          }
        )
      }
      
      //Begin Scene construction
      scene = new Scene {
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        
        //Arrange content in a Horizontal Box
        content = new VBox{ 
          padding = Insets(3)
          children = Seq(
            toolBar,
            new  VBox {
              children = Seq(
                  table
                  ,new Button {
                    text = "Show my claimed orders "
                    filtered = false
                  }
                //Table Creation
               //Table finished
              )
            }
          )
        }
      }  
    }
   stage
  }
  
  def buildTable(orders : ObservableBuffer[CustomerOrder]): TableView[CustomerOrder]={
    val table =  new TableView[CustomerOrder](orders){
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
        new TableColumn[CustomerOrder, String] {
          text = "Status"
          cellValueFactory = { _.value.status }
          prefWidth = 163
        }
      )
    }
    table
  }
  /*
  def updateTable(table : TableView[CustomerOrder], orders : ObservableBuffer[CustomerOrder]) : Unit = {
     orders = filter(orders)
     
     table.items.update(orders)
  }*/
  
}