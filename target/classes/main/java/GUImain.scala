
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.VBox
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.paint.Color._
import scalafx.geometry.Insets
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.layout.HBox
import scalafx.scene.image.Image
import javafx.scene.shape.Rectangle
import javafx.scene.paint.ImagePattern

object GUImain extends JFXApp {
    
  stage = new PrimaryStage {
    title = "Test"
    
    def createRect(): Rectangle ={
      val image = new Image("file:src/images/logo-white.png")
      val rect = new Rectangle(0,0,90,90)
      rect setFill(new ImagePattern(image))
      rect
    }
    
    val pic = createRect
    
    scene = new Scene{  
      fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
         
      content = new VBox{
        padding = Insets(200)
        children = Seq(new TextField
                      ,new HBox {
          children = Seq(
            new Button{
              text = "Log In"
              padding=Insets(10)
              style = "-fx-font-size: 16pt"
              onAction = handle{
                println("Log In") 
              }
            },
            new Button{
              text = "Exit"
              style = "-fx-font-size: 16pt"
              padding=Insets(10)
              onAction = handle{
                System exit(0)
              }
            }
          )
        }
        )
      }
      content += pic
    }
  }
}