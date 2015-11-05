
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
import scalafx.scene.control.Label
import scalafx.scene.effect.DropShadow
import scalafx.scene.control.PasswordField
import scalafx.beans.property.StringProperty

object GUImain extends JFXApp {
    
  val db = new jdbc()
  
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
      val userField = new TextField{
          promptText = "Username"
          margin=Insets(10)
        }
      
      var passField = new PasswordField{
          promptText = "Password"
          margin=Insets(10)
        }
      
      content = new VBox{
        padding = Insets(100)
        
        children = Seq(new Label{
          text = "Welcome to NBGardens!"
          style = "-fx-font-size: 24pt"
           effect = new DropShadow {
              color = DarkGreen
              radius = 25
              spread = 0.25

            }
          //fill = new LinearGradient(endX = 0, stops = Stops(Black, DarkGreen))
          margin= Insets(1,1,1,10)
        },userField,passField,new HBox {
          children = Seq(
            new Button{
              text = "Log In"
              margin=Insets(10,10,10,250)
              style = "-fx-font-size: 12pt"
              onAction = handle{
                val user = userField.text.getValue
                val pass = passField.text.getValue
                if(verifyLogin(user, pass)){
                  System.exit(0)
                }
              }
            },
            new Button{
              text = "Exit"
              style = "-fx-font-size: 12pt"
              margin=Insets(10)
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
  
  def verifyLogin(user: String, pass: String):Boolean={
    var i = 0
    var boole = false
    while(i<3){
      val list:Array[String] = db getLogin(i)
      if(user.equals(list(0)) && pass.equals(list(1))){
        println("LOGGED IN")
        boole = true
      }
      i+=1
    }
    boole
  }
  
}