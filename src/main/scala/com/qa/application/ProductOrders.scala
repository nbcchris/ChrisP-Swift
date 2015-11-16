package com.qa.application;

import com.qa.database.EmployeeSQL
import com.qa.database.ProductOrderSQL
import com.qa.entities.ProductOrder
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.geometry.Orientation
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.control.ComboBox
import scalafx.scene.control.Separator
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableColumn.sfxTableColumn2jfx
import scalafx.scene.control.TableView
import scalafx.scene.control.ToolBar
import scalafx.scene.control.Tooltip
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.Color.PaleGreen
import scalafx.scene.paint.Color.SeaGreen
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops


/**
 * @author ChrisPoole
 * 
 * ProductOrders is the page containing a list of all Product Orders where Employees
 * are able to claim orders to work on in other areas of the system
 * 
 */
class ProductOrders(user : String) extends JFXApp {
  
  
  /**
   * Constructs the Logo using an image file
   */
  def logo: ImageView = {
    val image = new Image("file:src/images/logo.png", 80, 80, true, true)
    val imgview = new ImageView(image)
    imgview
  }

  
  
  
  
  /**
  * method used to replace the PrimaryStage of the application and display new content
  * Returns a new PrimaryStage
  */
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Product Orders"
      resizable = false
      
      //Connect to the database
      val db = new ProductOrderSQL()
      val orders : ObservableBuffer[ProductOrder] = db.getOrders

      val table = buildTable(orders)

      val toolBar = buildTools(table)
      
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
                    prefWidth = 490
                    onAction = handle{
                      filterTable(table, orders) 
                    }
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
  
  
  
  
  /**
   * @Param table - the table to be update with filtered orders
   * @param orders - the orders to be filtered
   * Uses the filter function to update the table with only the Orders that belong to the current user
   */
  def filterTable(table : TableView[ProductOrder], orders: ObservableBuffer[ProductOrder]) : Unit = {
     val newOrders:ObservableBuffer[ProductOrder] = filter(orders)
     table.items.update(newOrders)
  }
  
  
  
  
  /**
   * @Param orders - an ObservableBuffer of ProductOrders to be filtered
   * Filters all orders by current users order id and returns them as an ObservableBuffer of ProductOrders
   */
  def filter(orders : ObservableBuffer[ProductOrder]): ObservableBuffer[ProductOrder] = {
    val empdb = new EmployeeSQL()
    val newOrders = (x : ProductOrder) =>  x.getId % empdb.getId(user) == 0
    for(x <- orders; if(newOrders(x))) yield x
  }
  
  
  
  
  /**
   * @Param orders - an ObservableBuffer of ProductOrders to be placed in tableView
   * Builds the table from a list of ProductOrder object
   */
  def buildTable(orders : ObservableBuffer[ProductOrder]): TableView[ProductOrder]={
    val table =  new TableView[ProductOrder](orders){
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
        }
      )
    }
    table
  }
  
  
  
  
  /**
   * @Param table  - the table to be updated
   * @Param orders - the orders to place in the table
   * Refreshes the table
   */
  def updateTable(table : TableView[ProductOrder], orders: ObservableBuffer[ProductOrder]){
    table.items.update(orders)
  }
  
  /**
   * buildCombo()
   * Builds the combo box for Status selection
   */
  def buildCombo : ComboBox[String] = {
     val combo : ComboBox[String] = new ComboBox()
      val options : ObservableBuffer[String] = ObservableBuffer[String]()
        options += "Picked"
        options += "Packed"
        options += "Dispatched"
        options += "Complete"
      combo.promptText = "Pick a Status"
      combo.items = options
      
      combo
  }
  
  
  
  
  /**
   * @Param table - the table that the toolbar will provide functionality for
   * Builds the Toolbar, uses table as an argument to provide functionality to table data
   */
  def buildTools( table : TableView[ProductOrder]) : ToolBar = {
    val db = new ProductOrderSQL()
    val combo = buildCombo
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
              val a = new POrder(user, getCoid(table))
              stage = a build
            }
          },
          new Button {
            id = "claim"
            text = "Claim Order"
            onMouseClicked = handle {
              val empdb = new EmployeeSQL()
              val db = new ProductOrderSQL()
              val userid = empdb getId(user)
              println(getCoid(table))
              db claim(getCoid(table), userid)
              updateTable(table, db getOrders)
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
                db updateStatus(getCoid(table),selected)
                val a = new ProductOrders(user)
                stage = a build
              }
            }
          }
        )
      }
      
    toolBar
  }
  
  
  
  /**
   * @Param table - the table whose selected value will be returned
   * gets the ProductOrderId for the currently selected tuple of the ProductOrder Table
   */
  def getCoid(table : TableView[ProductOrder]) : Int = {
    val coid = table.getSelectionModel.selectedItemProperty.get.productOrderId.value
    coid
  }
}