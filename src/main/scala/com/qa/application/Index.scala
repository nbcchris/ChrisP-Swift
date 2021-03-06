

package com.qa.application

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
import scalafx.scene.image.ImageView


/**
 * @author ChrisPoole
 * 
 * Index functions as a landing page post Log in
 * It contains a list of the pages in the system as menu options (Buttons)
 * This page is responsible for navigation between different pages in the system
 */
class Index(user : String) extends JFXApp {
  def build : PrimaryStage={
    stage = new PrimaryStage {
      title = "Index"
      resizable = false
      
      scene = new Scene{
        
       def logo(): ImageView = {
        val image = new Image("file:src/images/logo-white.png", 125, 125, true, true)
        val imgview = new ImageView(image)
        imgview
      }
        
        fill = new LinearGradient(endX = 0,stops = Stops(SeaGreen, PaleGreen))
        content = new VBox{
          children = Seq(
            new Label {
              id = "Logo"
              graphic = logo
              margin = Insets(60,200,10,184)
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