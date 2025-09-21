package scala

import worldLogic.classes.GameWorld
import scala.dsl.Program
import scala.dsl.Command
import dsl.Interpreter.execute
import scala.dsl.Interpreter.runProgram
import scala.dsl.Parser


def readFile(file: String) = {
    scala.io.Source.fromFile(file).getLines.mkString("\n")
}

object Run {
    def readFile(file: String) = {
        scala.io.Source.fromFile(file).getLines.mkString("\n")
    }

    //example interaction
    @main def main(): Unit = {
        val world = GameWorld(worldSize = 5)
        val parser = new Parser()
        val file = readFile("show.niki")
        val result = parser.parseProgram(file)
        runProgram(result, world)
    }
}