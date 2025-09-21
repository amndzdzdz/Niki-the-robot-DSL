package scala.dsl

case class Program(commands: List[Command])

enum Command {
    case TurnLeft
    case TurnRight
    case Move(steps: Int)
    case PickUp
    case Drop
    case ShowIf(condition: Condition)
    case IfCommand(condition: Condition, left: Program, right: Program)
    case WhileCommand(condition: Condition, program: Program)
}   

enum Condition {
    case True
    case False
    case SquareHasItems
    case RobotHasItems
    case IsRockAhead
    case IsEdgeAhead
    case NotCondition(condition: Condition)
    case AndConditions(leftCondition: Condition, rightCondition: Condition)
    case OrConditions(leftCondition: Condition, rightCondition: Condition)
}