package worldLogic.classes

import worldLogic.classes.State
import scala.compiletime.ops.double
import scala.util.Random
import worldLogic.interfaces.Square
import worldLogic.classes.EmptySquare
import scala.collection.mutable.ArrayBuffer
import worldLogic.classes.RockSquare
import worldLogic.classes.ItemSquare
import worldLogic.classes.Item

class GameWorld(worldSize: Int) {
    private val state: State = generateRandomState_()
    private val robot: Robot = Robot(worldSize)

    def generateRandomState_(): State = {
        var state = new ArrayBuffer[ArrayBuffer[Square]]
        var id = 0
        for row <- 0 until this.worldSize do
            var newRow = new ArrayBuffer[Square]
            for value <- 0 until this.worldSize do
                if (row == 0 && value == 0) {
                    val startSquare = new EmptySquare()
                    newRow.addOne(startSquare)
                } else {
                    val square = sampleSquare_(maxNumberItems = 10)
                    newRow.addOne(square)
                }
            state.addOne(newRow)
        return new State(state)
    } 

    def sampleSquare_(maxNumberItems: Int): Square = {
        //If returned value is between 0 and 0.6 we will return an EmptySquare Object
        //If returned value is between 0.6 and 0.8 we will return a RockSquare Object
        //If returned value is between 0.8 and 1.0 we will return a ItemSquare Object
        val p = scala.util.Random.nextDouble
        val itemIdx = scala.util.Random.nextInt(this.worldSize * this.worldSize)

        if p < 0.7 then
            return EmptySquare()
        else if p > 0.7 && p < 0.8 then
            return RockSquare()
        else
            return ItemSquare(
                new Item("Random Item", itemIdx)
            )
    }

    def move(steps: Int): Unit = {
        this.robot.moveForward(steps, this.state)
        val (x, y) = this.robot.getCurrentPosition()
        this.state.visualizeWorld(roboPosition = (x, y))
        println("---------------")
    }

    def visualzeStart(): Unit = {
        var (x, y) = this.robot.getCurrentPosition()
        this.state.visualizeWorld(x, y)
        println("---------------")
    }

    def turnLeft(): Unit = {
        this.robot.turnLeft()
    }

    def turnRight(): Unit = {
        this.robot.turnRight()
    }

    def tryPickUp(): Unit = {
        this.robot.tryPickUpItem(this.state)
    }

    def tryDrop(): Unit = {
        this.robot.tryDropItem(this.state)
    }

    def squareHasItems(): Boolean = {
        val (x, y) = this.robot.getCurrentPosition()
        val square = this.state.getSquareAtPosition(x, y)
        if (square.isInstanceOf[ItemSquare]) {
            return true
        } else {
            false
        }
    }

    def robotHasItems(): Boolean = {
        val items = this.robot.getItems()
        if (items.length > 0) {
            return true
        } else {
            return false
        }
    }

    def isRockAhead(): Boolean = {
        if (this.robot.checkForRocks(1, this.state)) {
            return true
        } else {
            return false
        }
    }

    def isEdgeAhead(): Boolean = {
        this.robot.checkForEdge(this.state)
    }
}
