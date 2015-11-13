

package Application

import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.VBox
import scalafx.scene.image.Image
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Rectangle
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.paint.Color._
import scalafx.scene.control.Button
import scalafx.scene.control.Tooltip
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.control.Label

class Index(user : String) extends JFXApp {
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Index"
      resizable = false
      
      scene = new Scene{
        
       def createRect(): Rectangle ={
          val image = new Image("file:src/images/logo-white.png")
          val rect = new Rectangle(0,0,100,100)
          rect setFill(new ImagePattern(image))
          rect
        }
        
        val pic = createRect
        val test = new Button
        
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        content = new VBox{
          children = Seq(
            new Label {
              id = "Logo"
              graphic = createRect
              margin = Insets(60,200,10,200)
            },new Button {
              id = "customerOrders"
              minWidth = 200
              margin = Insets(5,150,5,150)
              text = "Customer Orders"
              style = "-fx-font-size: 14pt"
              onAction = handle {
                val a = new CustomerOrders(user)
                stage = a build
              }
            },new Button {
              id = "customerOrders"
              minWidth = 200
              style = "-fx-font-size: 14pt"
              margin = Insets(5,150,5,150)
              text = "Product Orders"
              onAction = handle {
                val a = new ProductOrders(user)
                stage = a build
              }
            },new Button {
              id = "travellingSalesman"
              margin = Insets(5,150,5,150)
              style = "-fx-font-size: 14pt"
              text = "Travelling Salesman"
              minWidth = 200
              onAction = handle {
                val a = new Salesman(user)
                stage = a build
              }
            },new Button {
              id = "exit"
              margin = Insets(5,150,75,150)
              minWidth = 200
              style = "-fx-font-size: 14pt"
              text = "Exit System"
              onAction = handle {
                System.exit(0)
              }
            }
          )
        }
      }
    }
    stage
  }
}