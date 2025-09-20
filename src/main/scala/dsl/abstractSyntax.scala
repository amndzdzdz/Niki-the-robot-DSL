package scala.dsl

sealed trait Command

case class Program(commands: List[Command])

case class TurnLeft() extends Command
case class TurnRight() extends Command
case class moveForward(steps: Int) extends Command
case class PickUp() extends Command
case class Drop() extends Command