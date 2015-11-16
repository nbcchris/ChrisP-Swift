package com.qa.application;

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
import com.qa.entities.ProductOrder
import com.qa.database.ProductOrderSQL
import com.qa.database.EmployeeSQL
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
import scalafx.scene.control.ComboBox
import scalafx.scene.image.ImageView


/**
 * @author ChrisPoole
 * 
 * ProductOrders is the page containing a list of all Product Orders where Employees
 * are able to claim orders to work on in other areas of the system
 * 
 */
class ProductOrders(user : String) extends JFXApp {

  //method used to replace the PrimaryStage of the application and display new content
  //Returns a new PrimaryStage
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Product Orders"
      resizable = false
      
      var coid = 0
      //Connect to the database
      val db = new ProductOrderSQL()
      val empdb = new EmployeeSQL()
      //Pull all information about ProductOrders and store in 'order' buffer
      val order : ObservableBuffer[ProductOrder] = db getOrders
      
      scene = new Scene {
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        
        def logo: ImageView = {
          val image = new Image("file:src/images/logo.png", 80, 80, true, true)
          val imgview = new ImageView(image)
          imgview
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
            graphic = logo
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
              val a = new POrder(user, coid)
              stage = a build
            }
          },
          new Button {
            id = "claim"
            text = "Claim Order"
            onMouseClicked = handle {
              val userid = empdb getId(user)
              db claim(coid, userid)
              val a = new ProductOrders(user)
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
                val a = new ProductOrders(user)
                stage = a build
              }
            }
          }
        )
      }
        
      def buildTable: TableView[ProductOrder]={
        val order : ObservableBuffer[ProductOrder] = db.getOrders
      
        val table =  new TableView[ProductOrder](order) {
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
                new TableColumn[ProductOrder, String] {
                  text = "Status"
                  cellValueFactory = { _.value.status }
                  prefWidth = 163
                  // Render the property value when it changes, 
                  // including initial assignment
                  }
              )
            }//Table finished
        
        table.onMouseClicked = handle {
          try{
            coid = table.getSelectionModel.selectedItemProperty.get.productOrderId.value
          }catch{
            case e : Throwable => e printStackTrace
          }
        }
        table
      }
        
        
        //Arrange content in a Horizontal Box
        content = new VBox {
          padding = Insets(3)
          children = Seq(
              
              toolBar,
              
              //Table Creation
              buildTable
              
              
              
              
          )
        }
      }
    }
   stage
  }
}