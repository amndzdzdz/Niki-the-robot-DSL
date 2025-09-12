package classes

import interfaces.Square
import scala.collection.mutable.ArrayBuffer

case class EmptySquare() extends Square {
    def getContent(): ArrayBuffer[Item] = {
        return ArrayBuffer(Item())
    }
}