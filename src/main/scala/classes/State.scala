package classes

import interfaces.Square
import scala.collection.mutable.ArrayBuffer
import scala.compiletime.ops.double

class State(val world: ArrayBuffer[ArrayBuffer[Square]]) {
    assert(world.length > 0, "The world height must be larger than 0")

    private val worldHeight: Int = world.length
    private val worldWidth: Int = world(0).length

    def getItem(x: Int, y: Int): ArrayBuffer[Item] = {
        assert(world(y)(x).isInstanceOf[ItemSquare], "Square does not contain any items")
        var content = world(y)(x).getContent()
        world(y)(x) = EmptySquare()

        return content
    }

    def getSquare(x: Int, y: Int): Square = {
        world(y)(x)
    }

    def setItem(x: Int, y: Int, item: Item) = {
        world(y)(x) = ItemSquare(ArrayBuffer(item))
    }

    def visualizeWorld(roboPosition: (Int, Int)) = {
        var simplifiedWorld = new ArrayBuffer[ArrayBuffer[String]]()
        for row <- world do
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
}
