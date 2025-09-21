package scala.dsl

case class Program(commands: List[Command])

enum Command {
    case TurnLeft()
    case TurnRight()
    case MoveForward(steps: Int)
    case PickUp()
    case Drop()
}
