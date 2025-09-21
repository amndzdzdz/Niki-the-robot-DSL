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
            Command.Move(2),
            Command.TurnRight(),
            Command.Move(2),
            Command.PickUp(),
            Command.Move(1),
            Command.Drop()
        ))

        execute(exampleProgram, world)
    }
}