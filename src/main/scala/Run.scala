package scala 

import worldLogic.classes.GameWorld
import scala.dsl.Program
import scala.dsl.moveForward
import scala.dsl.TurnRight
import scala.dsl.PickUp
import scala.dsl.Drop
import dsl.Interpreter.execute

object Run {
    //example interaction
    @main def main(): Unit = {
        val world = GameWorld(worldSize = 5)
        val exampleProgram = new Program(List(
            new moveForward(2),
            new TurnRight(),
            new moveForward(2),
            new PickUp(),
            new moveForward(1),
            new Drop()
        ))

        execute(exampleProgram, world)
    }
}