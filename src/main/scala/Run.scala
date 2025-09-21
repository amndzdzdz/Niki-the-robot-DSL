package scala 

import worldLogic.classes.GameWorld
import scala.dsl.Program
import scala.dsl.Command
import dsl.Interpreter.execute

object Run {
    //example interaction
    @main def main(): Unit = {
        val world = GameWorld(worldSize = 5)
        val exampleProgram = new Program(List(
            Command.MoveForward(2),
            Command.TurnRight(),
            Command.MoveForward(2),
            Command.PickUp(),
            Command.MoveForward(1),
            Command.Drop()
        ))

        execute(exampleProgram, world)
    }
}