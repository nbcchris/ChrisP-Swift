
package com.qa.application

import com.qa.database.LoginSQL
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
import scalafx.scene.image.ImageView

/**
 * @author ChrisPoole
 * 
 * <p>
 * Main class for my application, this functions as the Login page and origin
 * of my GUI - it leads from here to the rest of my application but retains
 * the same PrimaryStage from this object throughout the rest of the Application
 * <p>
 */


object main extends JFXApp {
    
  //Connect to the Login SQL queries
  val db = new LoginSQL()
  

  //This PrimaryStage holds all GUI run in this application
  //it updates by replacing its scene with that of another window
  stage = new PrimaryStage {
    title = "Warehouse Order Tracking Application"
    resizable = false
    
    //This is the only instance of JavaFX - Used to frame the Logo
    def logo: ImageView = {
    val image = new Image("file:src/images/logo-white.png", 90, 90, true, true)
    val imgview = new ImageView(image)
    imgview
  }
    
    
    scene = new Scene{  
      fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
      val userField = new TextField{
          text = "mouse"
          promptText = "Username"
          margin=Insets(10)
        }
      
      var passField = new PasswordField{
          text = "pass"
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
          margin= Insets(1,1,1,25)
        },userField,passField,new HBox {
          children = Seq(
              
            //Log in button - this calls the verifyLogin method
            new Button{
              text = "Log In"
              margin=Insets(10,10,10,250)
              style = "-fx-font-size: 12pt"
              onAction = handle{
                val user = userField.text.getValue
                val pass = passField.text.getValue
                if(verifyLogin(user, pass)){
                  val a = new Index(user)
                  stage = a build
                }
              }
            },
            //Exit button to exit System
            new Button{
              text = "Exit"
              style = "-fx-font-size: 12pt"
              margin=Insets(10)
              onAction = handle{
                System exit(0)
              }
            }
           
          )//End of children declaration for HBox 
          
        }
        )
      }
      //Add the logo to the top right of the frame
      content += logo
    }
  }
  
  //This method uses the data from the database to verify the users input 
  def verifyLogin(user: String, pass: String):Boolean={
    if(db.getPass(user).equals(pass)) true else false
  }
}