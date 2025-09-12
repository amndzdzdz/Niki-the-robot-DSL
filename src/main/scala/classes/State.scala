package classes

import interfaces.Square
import scala.collection.mutable.ArrayBuffer
import scala.compiletime.ops.double
import classes.Item
import classes.ItemSquare
import classes.EmptySquare

class State(val world: ArrayBuffer[ArrayBuffer[Square]]) {
    assert(this.world.length > 0, "The world height must be larger than 0")

    private val worldHeight: Int = world.length
    private val worldWidth: Int = world.length

    def getItemAtPosition(x: Int, y: Int): Item = {
        val field = this.world(y)(x).asInstanceOf[ItemSquare]
        val item = field.getItem()
        world(y)(x) = EmptySquare()
        item
    }

    def getSquareAtPosition(x: Int, y: Int): Square = {
        this.world(y)(x)
    }

    def setItemAtPosition(x: Int, y: Int, item: Item) = {
        this.world(y)(x) = ItemSquare(item)
    }

    def visualizeWorld(roboPosition: (Int, Int)) = {
        var simplifiedWorld = new ArrayBuffer[ArrayBuffer[String]]()
        for row <- this.world do
            var newRow = new ArrayBuffer[String]
            for value <- row do 
                if value.isInstanceOf[EmptySquare] then
                    newRow.addOne("\u2B1C")
                else if value.isInstanceOf[ItemSquare] then
                    newRow.addOne("\uD83C\uDF81")
                else
                    newRow.addOne("\uD83D\uDDFF")
            simplifiedWorld.addOne(newRow)
        simplifiedWorld(roboPosition(1))(roboPosition(0)) = "\uD83E\uDD16"
        simplifiedWorld.map(_.mkString(" ")).foreach(println)
    }

    def getWidth(): Int = {
        return this.worldWidth
    }
}
