package scala.dsl

import fastparse._
import fastparse.Parsed.Failure
import fastparse.Parsed.Success

// Background information: We are using so-called parser combinators,
// which can be seen as a generalization of regular expressions to allow some
// form of recursion, and which are similar but not quite the same as
// context-free grammars:
// https://en.wikipedia.org/wiki/Parser_combinator

// Library documentation: https://com-lihaoyi.github.io/fastparse

// This line imports some implicit mechanisms that enable parsing that ignores
// white space.
import MultiLineWhitespace._

class Parser() {
  // A parser fragment that reads a sequence of numeric characters and converts
  // these into a plain integer value
  def number[$: P]: P[Int] = P(CharIn("0-9").rep(1).!.map(_.toInt))

  // Abbreviation for moving one step, represented with no additional command
  def step[$: P] =
    P("step" ~ ";" map { _ => Command.Move(1) })

  def move[$: P] =
    P("move" ~ "forward" ~ number ~ ";" map { steps => Command.Move(steps) })

  // Abbreviation for turning twice, represented as an additional command
  def turnAround[$: P] =
    P("turn" ~ "around" ~ ";" map { _ => Command.TurnAround })

  def turnLeft[$: P] =
    P("turn" ~ "left" ~ ";" map { _ => Command.TurnLeft })

  def turnRight[$: P] =
    P("turn" ~ "right" ~ ";" map { _ => Command.TurnRight })

  def pickUp[$: P] =
    P("pick" ~ "up" ~ ";" map { _ => Command.PickUp })

  def placeDown[$: P] =
    P("place" ~ "down" ~ ";" map { _ => Command.PlaceDown })

  // New commands here:
  def showIf[$: P] =
    P("show" ~ "if" ~ condition map { condition =>
      ???
    })

  def ifCommand[$: P] =
    P("if" ~ condition ~ "then" ~ program ~ "else" ~ program ~ "end" map {
      case (condition, left, right) =>
        ???
    })

  def whileCommand[$: P] =
    P("while" ~ condition ~ "do" ~ program ~ "end" map {
      case (condition, body) =>
        ???
    })

  // Parser for conditions starts here:
  def trivial[$: P] =
    P("true" map { _ => Condition.True })

  // Useful for testing. You can choose how you want to implement this.
  def absurd[$: P] =
    P("false" map { _ => ??? })

  def squareHasItems[$: P] =
    P("square" ~ "has" ~ "items" map { _ => ??? })

  def robotHasItems[$: P] =
    P("robot" ~ "has" ~ "items" map { _ => ??? })

  def isRockAhead[$: P] =
    P("is" ~ "rock" ~ "ahead" map { _ => ??? })

  def isEdgeAhead[$: P] =
    P("is" ~ "edge" ~ "ahead" map { _ => ??? })

  def not[$: P] =
    P("not" ~ basicCondition map { inner => ??? })

  def conjunction[$: P]: ParsingRun[Condition] =
    P(basicCondition ~ ("and" ~ basicCondition).rep map { case (first, rest) =>
      // You can use the following code, possibly rename the class And to your naming
      // rest.fold(first)(Condition.And(_, _))
      ???
    })

  def disjunction[$: P]: ParsingRun[Condition] =
    P(conjunction ~ ("or" ~ conjunction).rep map { case (first, rest) =>
      // You can use the following code, possibly rename the class Or to your naming
      // rest.fold(first)(Condition.Or(_, _))
      ???
    })

  def condition[$: P]: ParsingRun[Condition] =
    disjunction

  def basicCondition[$: P]: ParsingRun[Condition] =
    ("(" ~ condition ~ ")") | trivial | absurd | squareHasItems | robotHasItems | isRockAhead | isEdgeAhead | not

  // This is a choice between all the grammar rules defined above
  def command[$: P]: ParsingRun[Command] =
    step | move | turnLeft | turnRight | turnAround | pickUp | placeDown | showIf | ifCommand | whileCommand

  def program[$: P] =
    P(command.rep map { commands => Program(commands.toList) })

  def line[$: P] =
    command ~ End

  def file[$: P] =
    program ~ End

  def parseText(text: String): Option[Command] = {
    val result = parse(text, line(_))

    result match {
      case failure: Failure =>
        println(failure)
        None

      case Success(value, index) =>
        Some(value)
    }
  }

  def parseProgram(text: String): Option[Program] = {
    val result = parse(text, file(_))

    result match {
      case failure: Failure =>
        println(failure.extra.trace())
        None

      case Success(value, index) =>
        Some(value)
    }
  }
}
