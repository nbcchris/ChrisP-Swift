

package testpack

trait Animal {
  def eat (food:Any):Boolean={
    !food.==(null)
  }
  def grow()
  def reproduce(mate:Animal):Boolean
}