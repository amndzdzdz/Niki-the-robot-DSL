package classes

import classes.Item
import scala.collection.mutable.ArrayBuffer
import interfaces.Square

case class ItemSquare(var contents: ArrayBuffer[Item]) extends Square
