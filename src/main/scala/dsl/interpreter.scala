package scala.dsl

import dsl.Program
import worldLogic.classes.GameWorld

object Interpreter:
    // This method executes commands, those that change a state and don't return a value.
    def execute(program: Program, world: GameWorld) = {
        var commands = program.commands
        for (command <- commands) {
            command match
                case TurnLeft() => 
                    world.turnLeft()
                case TurnRight() =>
                    world.turnRight()
                case moveForward(steps) =>
                    world.move(steps)
                case PickUp() =>
                    world.tryPickUp()
                case Drop() =>
                    world.tryDrop()
        }
    }