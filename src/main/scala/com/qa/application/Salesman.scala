

package com.qa.application

import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Rectangle
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops

/**
 * @author ChrisPoole
 * 
 * TODO: Salesman is the page that will contain the Travelling Salesman Algorithm
 * 
 */

class Salesman(user : String) extends JFXApp {
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Travelling Salesman"
      resizable = false

      scene = new Scene{
        
       def createRect(): Rectangle ={
          val image = new Image("file:src/images/logo-white.png")
          val rect = new Rectangle(0,0,100,100)
          rect setFill(new ImagePattern(image))
          rect
        }
        
        val pic = createRect
        
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        content = new VBox{
          children = Seq(
            new Label {
              id = "Logo"
              graphic = createRect
              margin = Insets(60,200,10,200)
            },
            new Label {
              text = "Nothing here yet"
              style = "-fx-font-size: 18pt"
              margin = Insets(60,170,10,160)
            },
            new Button {
              id = "exit"
              margin = Insets(5,150,75,150)
              minWidth = 200
              style = "-fx-font-size: 14pt"
              text = "Take me back"
              onAction = handle {
                val a = new Index(user)
                stage = a build
              }
            }
          )
        }
      }
    }
    stage
  }
}