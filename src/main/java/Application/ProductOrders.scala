package Application;

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
import Entities.ProductOrder
import Database.ProductOrderSQL
import scalafx.scene.shape.Circle
import scalafx.scene.control.ToolBar
import scalafx.scene.control.ToggleGroup
import scalafx.scene.control.Tooltip
import scalafx.scene.control.ToggleButton
import scalafx.scene.control.Separator
import scalafx.geometry.Orientation
import scalafx.event.ActionEvent
import scalafx.scene.paint.Color._
import scalafx.scene.image.Image
import javafx.scene.shape.Rectangle
import javafx.scene.paint.ImagePattern
import scalafx.scene.control.ComboBox


/**
 * @author ChrisPoole
 * 
 * 
 */
class ProductOrders extends JFXApp {

  //method used to replace the PrimaryStage of the application and display new content
  //Returns a new PrimaryStage
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Product Orders"
      
      
      //Connect to the database
      val db = new ProductOrderSQL()
      //Pull all information about ProductOrders and store in 'order' buffer
      val order : ObservableBuffer[ProductOrder] = db getOrders
      
      scene = new Scene {
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        
        def createRect(): Rectangle ={
          val image = new Image("file:src/images/logo.png")
          val rect = new Rectangle(0,0,80,80)
          rect setFill(new ImagePattern(image))
          rect
        }
        
        val combo : ComboBox[String] = new ComboBox()
        val options : ObservableBuffer[String] = ObservableBuffer[String]()
        options += "Placed"
        options += "Dispatched"
        options += "Received"
        options += "Processed"
        combo.promptText="Pick a Status"
        combo.items = options
        
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
        
        /*
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
              id = "editButton"
              text = "Customer Orders"
            },
            new Button {
              id = "deleteButton"
              text = "Product Orders"
              graphic = new Circle {
                fill = Blue
                radius = 8
              }
            },
            new Separator {
              orientation = Orientation.VERTICAL
            },
            new ToggleButton {
              id = "boldButton"
              graphic = new Circle {
                fill = Maroon
                radius = 8
              }
              onAction = {
                e: ActionEvent =>
                  val tb = e.getTarget.asInstanceOf[javafx.scene.control.ToggleButton]
                  print(e.eventType + " occurred on ToggleButton " + tb.id)
                  print(", and selectedProperty is: ")
                  println(tb.selectedProperty.value)
              }
            },
            new ToggleButton {
              id = "italicButton"
              graphic = new Circle {
                fill = Yellow
                radius = 8
              }
              onAction = {
                e: ActionEvent =>
                  val tb = e.getTarget.asInstanceOf[javafx.scene.control.ToggleButton]
                  print(e.eventType + " occurred on ToggleButton " + tb.id)
                  print(", and selectedProperty is: ")
                  println(tb.selectedProperty.value)
              }
            },
            new Separator {
              orientation = Orientation.VERTICAL
            },
            new ToggleButton {
              id = "leftAlignButton"
              toggleGroup = alignToggleGroup
              graphic = new Circle {
                fill = Purple
                radius = 8
              }
            },
            new ToggleButton {
              toggleGroup = alignToggleGroup
              id = "centerAlignButton"
              graphic = new Circle {
                fill = Orange
                radius = 8
              }
            },
            new ToggleButton {
              toggleGroup = alignToggleGroup
              id = "rightAlignButton"
              graphic = new Circle {
                fill = Cyan
                radius = 8
              }
            }
          )
        }
    
        alignToggleGroup.selectToggle(alignToggleGroup.toggles(0))
        alignToggleGroup.selectedToggle.onChange {
          val tb = alignToggleGroup.selectedToggle.get.asInstanceOf[javafx.scene.control.ToggleButton]
          println(tb.id() + " selected")
        }*/
        
        
        //Arrange content in a Horizontal Box
        content = new VBox {
          padding = Insets(3)
          children = Seq(
              
              toolBar,
              
              //Table Creation
              new TableView[ProductOrder](order) {
              columns ++= List(
                new TableColumn[ProductOrder, Int] {
                  text = "Order ID" 
                  cellValueFactory = { _.value.productOrderId }
                  prefWidth = 163
                },
                new TableColumn[ProductOrder, Int]() {
                  text = "Employee ID"
                  cellValueFactory = { _.value.employeeId }
                  prefWidth = 163
                },
                new TableColumn[ProductOrder, Int] {
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
      }
    }
   stage
  }
}