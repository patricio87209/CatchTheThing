package ar.pablitar.catchthething


import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Color
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import scala.util.Random
import scala.collection.immutable.List


class Score(scene: CatchTheThingScene) extends RichGameComponent[CatchTheThingScene] with SpeedyComponent[CatchTheThingScene] {
  var maxCombo = 0
  var combo = 0
  speed = Vector2D(0.0 , 40)
  var randomColor  = Color.YELLOW
  this.setScene(scene)
  this.updateLabel
  var randomNumberForColor = 1
  
def getColor :Color = {
    randomNumberForColor = Random.nextInt(4)
    if(randomNumberForColor == 0)randomColor = Color.YELLOW
    if(randomNumberForColor == 1)randomColor = Color.RED
    if(randomNumberForColor == 2)randomColor = Color.BLUE
    if(randomNumberForColor == 3)randomColor = Color.PINK
    return randomColor    
  }
  
  def scorePoints =  1 + (1 * scene.score.combo)
  
  this.setX(scene.catcher.position.x1)
  this.setY(scene.catcher.position.x2)
  
  def updateLabel = {
    //this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.RED, "Score: " + score))
    this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.ITALIC, 35), getColor , " "+ scorePoints))
    //this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.GREEN, "MaxCombo: " + maxCombo))
    //this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.BLUE, "Combo: " + combo))
  }
  
  def eraseSlowly(state: DeltaState) { 
    this.setY(this.speed.x2 * state.getDelta())
  }
  
  override def update(state: DeltaState) = {
    super.update(state)
    this.checkIfBelowTheScreen()
  }
    
   def checkIfBelowTheScreen() = {
    if (this.position.x2 > 650){
        this.destroy()
      }
   }
}