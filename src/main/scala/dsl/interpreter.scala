package scala.dsl

import dsl.Program
import dsl.Command
import worldLogic.classes.GameWorld

object Interpreter:
    // This method executes commands, those that change a state and don't return a value.
    def execute(program: Program, world: GameWorld) = {
        var commands = program.commands
        for (command <- commands) {
            command match
                case Command.TurnLeft() => world.turnLeft()
                case Command.TurnRight() => world.turnRight()
                case Command.MoveForward(steps) => world.move(steps)
                case Command.PickUp() => world.tryPickUp()
                case Command.Drop() => world.tryDrop()
        }
    }