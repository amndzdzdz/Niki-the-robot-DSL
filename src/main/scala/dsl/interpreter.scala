package scala.dsl

import dsl.Program
import dsl.Command
import dsl.Condition
import worldLogic.classes.GameWorld
import scala.compiletime.ops.boolean

object Interpreter {
    // This method executes commands, those that change a state and don't return a value.
    def execute(command: Command, world: GameWorld) = {
        command match
            case Command.TurnLeft() => world.turnLeft()
            case Command.TurnRight() => world.turnRight()
            case Command.Move(steps) => world.move(steps)
            case Command.PickUp() => world.tryPickUp()
            case Command.Drop() => world.tryDrop()
            case Command.ShowIf(condition)  =>
                if(holds(condition, world)) {
                    println("condition " + condition + " holds")
                } else {
                    println("condition " + condition + " does not hold")
                }
            case Command.IfCondition(condition, program) => ???
            case Command.WhileCondition(condition, program) => ???
    }
    def holds(condition: Condition, world: GameWorld): Boolean = {
        condition match
            case Condition.True() => 
                return true
            case Condition.SquareHasItems() =>
                ???
            case Condition.RobotHasItems() =>
                ???
            case Condition.IsRockAhead() =>
                ???
            case Condition.IsEdgeAhead() =>
                ???
            case Condition.NotCondition(condition) =>
                ???
            case Condition.AndConditions(leftCondition, rightCondition) =>
                ???
            case Condition.OrConditions(leftCondition, rightCondition) =>
                ???
            case Condition.BracketCondition(condition) =>
                ???
        
        }
    def runProgram(program: Program, world: GameWorld) = {
        var commands = program.commands
        for (command <- commands) {
            execute(command, world)
            }
        }
    }