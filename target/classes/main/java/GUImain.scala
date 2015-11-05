
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.paint.Color._
import scalafx.geometry.Insets

object GUImain extends JFXApp {
  
  stage = new PrimaryStage {
    title = "Test"
    scene = new Scene{  
      fill = Black
      content = new VBox {
        padding = Insets(200)
        content = Seq(
           new Button{
             text = "Hello?"
             style = "-fx-font-size: 48pt"
             onAction = handle{
               println("Hello World") 
            }
           },
          new Button{
             text = "Bye?"
             style = "-fx-font-size: 48pt"
             onAction = handle{
               println("Goodbye World") 
            }
          }
        )
     }
    }  
  }
}