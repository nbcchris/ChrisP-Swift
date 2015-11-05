
import scalafx.Includes.handle
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.control.Button.sfxButton2jfx
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.VBox
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color._
import scalafx.scene.layout.Border
import scalafx.scene.layout.FlowPane
import scalafx.scene.layout.HBox
import scalafx.scene.layout.AnchorPane
import scalafx.event.ActionEvent

object GUImain extends JFXApp {
  
  stage = new PrimaryStage {
    title = "Fade test"
    width = 1000
    height = 800 
  }
  
  private val btn = new Button("Hello?")
  btn.onAction = handle{
    println("Hello World") 
  }
  
  private val btn1 = new Button("Bye?")
  btn1.onAction = handle{
    println("Goodbye World") 
  }
  

  private val theScene = new Scene {
    fill = Black
    content = new GridPane {
      add(btn, 0, 0)
      addColumn(1, btn1)
    }

    //root = theRoot
  }
  stage.scene = theScene
}