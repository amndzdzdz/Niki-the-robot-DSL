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
            case Command.TurnLeft => world.turnLeft()
            case Command.TurnRight => world.turnRight()
            case Command.Move(steps) => world.move(steps)
            case Command.PickUp => world.tryPickUp()
            case Command.Drop => world.tryDrop()
            case Command.ShowIf(condition)  =>
                if(holds(condition, world)) {
                    println("condition " + condition + " holds")
                } else {
                    println("condition " + condition + " does not hold")
                }
            case Command.IfCommand(condition, left, right) =>
                if (holds(condition, world)) {
                    runProgram(Some(left), world)
                } else {
                    runProgram(Some(right), world)
                }
            case Command.WhileCommand(condition, program) => 
                while (holds(condition, world)) {
                    runProgram(Some(program), world)
                }
    }
    def holds(condition: Condition, world: GameWorld): Boolean = {
        condition match
            case Condition.True => 
                return true
            case Condition.False => 
                return false
            case Condition.SquareHasItems =>
                return world.squareHasItems()
            case Condition.RobotHasItems =>
                world.robotHasItems()
            case Condition.IsRockAhead =>
                return world.isRockAhead()
            case Condition.IsEdgeAhead =>
                return world.isEdgeAhead()
            case Condition.NotCondition(condition) =>
                return !holds(condition, world)
            case Condition.AndConditions(leftCondition, rightCondition) =>
                if (holds(leftCondition, world) && holds(rightCondition, world)) {
                    return true
                } else {
                    return false
                }
            case Condition.OrConditions(leftCondition, rightCondition) =>
                if (holds(leftCondition, world) || holds(rightCondition, world)) {
                    return true
                } else {
                    return false
                }
        }
    def runProgram(program: Option[Program], world: GameWorld): Unit = {
        program match
            case Some(value) => 
                var commands = value.commands
                for (command <- commands) {
                    execute(command, world)
                    }
                }
    }
