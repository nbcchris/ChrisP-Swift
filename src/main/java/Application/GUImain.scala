
package Application

import Database.LoginSQL
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Rectangle
import scalafx.Includes.handle
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.StringProperty.sfxStringProperty2jfx
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.PasswordField
import scalafx.scene.control.TextField
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color.DarkGreen
import scalafx.scene.paint.Color.PaleGreen
import scalafx.scene.paint.Color.SeaGreen
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops

object GUImain extends JFXApp {
    
  val db = new LoginSQL()
  

  
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
          margin= Insets(1,1,1,25)
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
                  val a = new CustomerOrders
                  stage = a build
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