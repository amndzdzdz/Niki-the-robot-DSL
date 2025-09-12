package scala 

import scala.compiletime.ops.double
import classes.*
import interfaces.Square
import scala.collection.mutable.ArrayBuffer

object Experiments {
    val program = Program(worldSize = 5)

@main def main(): Unit = {
    val state = program.getState()
    val niki = program.getRobot()
    var (x, y) = niki.getCurrentPosition()
    state.visualizeWorld(x, y)
    println("------------------------")
    niki.moveForward(nSteps = 3, state)
    niki.pickUpItems(state)
    var (xn, yn) = niki.getCurrentPosition()
    state.visualizeWorld(xn, yn)
    println("------------------------")

    niki.turnLeft()
    niki.turnLeft()
    niki.moveForward(nSteps = 2, state)
    var (x2, y2) = niki.getCurrentPosition()
    state.visualizeWorld(x2, y2)
    niki.dropItem(state)
    println("------------------------")
    niki.moveForward(nSteps = 20, state)
    var (x11, y11) = niki.getCurrentPosition()
    state.visualizeWorld(x11, y11)
    println("------------------------")

}
}