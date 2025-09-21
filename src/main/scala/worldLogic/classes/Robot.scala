package worldLogic.classes

import scala.collection.mutable.ArrayBuffer
import worldLogic.enums.Orientation
import java.util.ArrayList
import scala.compiletime.ops.double
import worldLogic.classes.Item
import worldLogic.classes.RockSquare
import worldLogic.classes.ItemSquare

class Robot(worldSize: Int) {
    private var items: ArrayBuffer[Item] = ArrayBuffer()
    private var currentPositionX: Int = 0
    private var currentPositionY: Int = 0
    private var currentOrientation: Orientation = Orientation.RIGHT

    def turnLeft(): Unit = {
        currentOrientation match
            case Orientation.UP => this.currentOrientation = Orientation.LEFT
            case Orientation.RIGHT => this.currentOrientation = Orientation.UP
            case Orientation.DOWN => this.currentOrientation = Orientation.LEFT
            case Orientation.LEFT => this.currentOrientation = Orientation.DOWN
    }

    def getCurrentPosition(): (Int, Int) = {
        (this.currentPositionX, this.currentPositionY)
    }

    def turnRight(): Unit = {
        currentOrientation match
            case Orientation.UP => this.currentOrientation = Orientation.RIGHT
            case Orientation.RIGHT => this.currentOrientation = Orientation.DOWN
            case Orientation.DOWN => this.currentOrientation = Orientation.RIGHT
            case Orientation.LEFT => this.currentOrientation = Orientation.UP
    }

    def tryPickUpItem(state: State): Unit = {
        if (state.getSquareAtPosition(this.currentPositionX, this.currentPositionY).isInstanceOf[ItemSquare]) {
            var pickedUpItem = state.getItemAtPosition(this.currentPositionX, this.currentPositionY)
            items.addOne(pickedUpItem)
            println("I just picked up an item!")
        } else {
            println("There are no items to be picked up!")
        }
    }

    def tryDropItem(state: State, itemIdx: Int = 0): Unit = {
        assert(itemIdx <= items.length, "This item index is bigger than the item list")

        if (items.length > 0) {
            val item = items(itemIdx)
            items.remove(itemIdx)
            state.setItemAtPosition(this.currentPositionX, this.currentPositionY, item)
            println("I just dropped an item!")
        } else {
            println("I don't have any items to drop :(")
        }
    }

    def moveForward(nSteps: Int, state: State): Unit = {
        this.currentOrientation match
            case Orientation.UP => 
                if checkForRocks(nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if this.currentPositionY - nSteps > 0 then 
                    this.currentPositionY = this.currentPositionY - nSteps else this.currentPositionY = 0
            
            case Orientation.RIGHT => 
                if checkForRocks(nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if this.currentPositionX + nSteps < this.worldSize then 
                this.currentPositionX = this.currentPositionX + nSteps else this.currentPositionX = this.worldSize-1
            
            case Orientation.DOWN => 
                if checkForRocks(nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if this.currentPositionY + nSteps < this.worldSize then 
                this.currentPositionY = this.currentPositionY + nSteps else this.currentPositionY = this.worldSize-1
            
            case Orientation.LEFT => 
                if checkForRocks(nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if this.currentPositionX - nSteps > 0 then 
                this.currentPositionX = this.currentPositionX - nSteps else this.currentPositionX = 0
    }

    def checkForRocks(nSteps: Int, state: State): Boolean = {
        var bool = false
        this.currentOrientation match
            case Orientation.UP =>
                if (this.currentPositionY == 0) {
                    return false 
                }

                for step <- 1 until nSteps do
                    var nextStep = this.currentPositionY - step
                    if nextStep < 0 then
                        nextStep = 0
                    var square = state.getSquareAtPosition(this.currentPositionX, nextStep)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
            
            case Orientation.RIGHT => 
                if (this.currentPositionX == this.worldSize-1) {
                    return false 
                }

                var maxSteps = 0
                if this.currentPositionX + nSteps >= this.worldSize then
                    maxSteps = this.currentPositionX
                else 
                    maxSteps = nSteps + 1

                for step <- 1 until maxSteps do
                    var nextStep = this.currentPositionX + step
                    var square = state.getSquareAtPosition(nextStep, this.currentPositionY)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
            
            case Orientation.DOWN =>
                if (this.currentPositionY == this.worldSize-1) {
                    return false 
                }

                for step <- 1 until nSteps do
                    var nextStep = this.currentPositionY + step
                    if nextStep >= this.worldSize then
                        nextStep = this.worldSize - 1
                    var square = state.getSquareAtPosition(this.currentPositionX, nextStep)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
            
            case Orientation.LEFT =>
                if (this.currentPositionX == 0) {
                    return false 
                }
                var maxSteps = 0
                if this.currentPositionX - nSteps >= 0 then
                    maxSteps = nSteps
                else 
                    maxSteps = this.currentPositionX

                for step <- 1 until maxSteps do
                    var nextStep = this.currentPositionX - step
                    var square = state.getSquareAtPosition(nextStep, this.currentPositionY)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
        return bool
    }

    def checkForEdge(state: State): Boolean = {
        this.currentOrientation match
            case Orientation.UP => 
                if (this.currentPositionY == 0) {
                    return true
                } else {
                    return false
                }
            
            case Orientation.RIGHT => 
                if (this.currentPositionX == this.worldSize-1) {
                    return true
                } else {
                    return false
                }

            case Orientation.DOWN =>
                if (this.currentPositionY == this.worldSize-1) {
                    return true
                } else {
                    return false
                }
             
            case Orientation.LEFT => 
                if (this.currentPositionX == 0) {
                    return true
                } else {
                    return false
                }
    }

    def getItems(): ArrayBuffer[Item] = {
        return this.items
    }
}

