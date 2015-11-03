
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.stage.StageStyle
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

object GUImain extends JFXApp {
  val characters = ObservableBuffer[Person](
    new Person("Peggy", "Sue", Color.Violet),
    new Person("Rocky", "Raccoon", Color.GreenYellow),
    new Person("Bungalow ", "Bill", Color.DarkSalmon)
  )

  stage = new PrimaryStage {
    title = "TableView with custom color cell"
    scene = new Scene {
      content = new TableView[Person](characters) {
        columns ++= List(
          new TableColumn[Person, String] {
            text = "Order ID"
            cellValueFactory = { _.value.firstName }
            prefWidth = 100
          },
          new TableColumn[Person, String]() {
            text = "Claimed"
            cellValueFactory = { _.value.lastName }
            prefWidth = 100
          },
          new TableColumn[Person, Color] {
            text = "Status"
            cellValueFactory = { _.value.favoriteColor }
            cellFactory = { _ => 
              new TableCell[Person, Color] {
                item.onChange { (_, _, newColor) => 
                  graphic = new Circle {fill = newColor; radius = 8}
                }
              }
            }
            prefWidth = 100
          }
        )
      }
    }
  }
  //HELLO WORLD EXAMPLE
  /*stage = new PrimaryStage {
    initStyle(StageStyle.UNIFIED)
    title = "ScalaFX Hello World"
    scene = new Scene {
      fill = Color.rgb(38, 38, 38)
      content = new HBox {
        padding = Insets(50, 80, 50, 80)
        children = Seq(
          new Text {
            text = "Hello"
            style = "-fx-font: normal bold 100pt sans-serif"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          },
          new Text {
            text = "World"
            style = "-fx-font: italic bold 100pt sans-serif"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(Cyan, White)
            )
            effect = new DropShadow {
              color = DarkGray
              radius = 15
              spread = 0.25
            }
          },
          new Text {
            text = "!!!"
            style = "-fx-font: normal bold 100pt sans-serif"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(DodgerBlue, Cyan))
          }
        )
      }
    }
  }*/
}