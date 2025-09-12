package classes

import classes.Item
import scala.collection.mutable.ArrayBuffer
import interfaces.Square

case class ItemSquare(val contents: ArrayBuffer[Item]) extends Square {
    def getContent(): ArrayBuffer[Item] = {
        return contents
    }
}
