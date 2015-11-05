
import scalafx.Includes.handle
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.paint.Color._

object GUImain extends JFXApp {
  
  stage = new PrimaryStage {
    title = "Test"
    width = 1000
    height = 800 
    scene = new Scene{  
      fill = Black
      content = new VBox {
        padding = Insets(20)
        content = Seq(
          new Button("Hello?"){
            onAction = handle{
              println("Hello World") 
            }
           },
          new Button("Bye?")
            onAction = handle{
              println("Goodbye World") 
            }  
          }
      )
    }
  }
}