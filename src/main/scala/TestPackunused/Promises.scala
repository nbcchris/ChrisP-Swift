
package TestPackunused

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.Random

object Promises {
  def main(args: Array[String]){
    val calculateMyScore = Future {
      Thread.sleep(500)
      new Random().nextInt(10)
    }
    val calculateYourScore = Future{
      Thread.sleep(500)
      new Random().nextInt(10)
    }
    val doIWin = for {
      myScore <- calculateMyScore
      yourScore <- calculateYourScore
    }yield myScore > yourScore
    
    doIWin onSuccess{
      case b: Boolean =>
        println(if(b) "yes" else "no")
    }
    println("Do I win?")
    Thread.sleep(2000)
    /*val sayHello = Future {
      Thread.sleep(1000)
      "hello"
    }
    
    sayHello onSuccess {
      case message =>
         println("He said "+message)
    }
    
    println("Waiting...")
    Thread.sleep(2000)*/
    
    /*
    val tryDivideByZero = Future{
      Thread.sleep(1000)
      1/0
    }
    
    tryDivideByZero onFailure {
      case e: ArithmeticException => println("Don't be silly!")
    }
    
    println("Try dividing by zero")
    Thread.sleep(2000)*/
    /*
    val firstLove = Future {
      Thread.sleep(500)
      "I love you"
    }
    
    val thenBetray = firstLove map {
      case loveLetter => {
        println(loveLetter)
        Thread.sleep(500)
        "Not really"
      }
    }
    thenBetray onSuccess{
      case partingWords =>
        println(partingWords)
    }
    Thread.sleep(2000)*/
  }
}