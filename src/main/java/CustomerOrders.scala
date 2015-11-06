



import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.layout.HBox
import scalafx.geometry.Insets
import scalafx.scene.control.Label

class CustomerOrders extends JFXApp {

  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Order List"
      height = 700
      width = 1000
      val db = new jdbc()
      val order : ObservableBuffer[CustomerOrder] = db.getOrders
      
      scene = new Scene {
        content = new HBox {
          padding = Insets(100,0,0,300)
          children = Seq(
              new Label{
                text = "Customer Order List"
                style = "-fx-font-size: 24pt"
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