package classes

import scala.collection.mutable.ArrayBuffer
import enums.Orientation
import java.util.ArrayList
import scala.compiletime.ops.double

class Robot(worldSize: Int) {
    var items: ArrayBuffer[Item] = ArrayBuffer()
    var currentPositionX: Int = 0
    var currentPositionY: Int = 0
    var currentOrientation: Orientation = Orientation.RIGHT

    def turnLeft(): Unit = {
        currentOrientation match
            case Orientation.UP => currentOrientation = Orientation.LEFT
            case Orientation.RIGHT => currentOrientation = Orientation.UP
            case Orientation.DOWN => currentOrientation = Orientation.LEFT
            case Orientation.LEFT => currentOrientation = Orientation.DOWN
    }

    def getCurrentPosition(): (Int, Int) = {
        (currentPositionX, currentPositionY)
    }

    def turnRight(): Unit = {
        currentOrientation match
            case Orientation.UP => currentOrientation = Orientation.RIGHT
            case Orientation.RIGHT => currentOrientation = Orientation.DOWN
            case Orientation.DOWN => currentOrientation = Orientation.RIGHT
            case Orientation.LEFT => currentOrientation = Orientation.UP
    }

    def pickUpItems(state: State): Unit = {
        var pickedUpItems = state.getItem(currentPositionX, currentPositionY)
        items ++= pickedUpItems
    }

    def dropItem(state: State, itemIdx: Int = 0): Unit = {
        assert(itemIdx <= items.length, "This item index is bigger than the item list")

        var item = items(itemIdx)
        items.remove(itemIdx)
        state.setItem(currentPositionX, currentPositionY, item)
    }

    def moveForward(nSteps: Int, state: State): Unit = {
        currentOrientation match
            case Orientation.UP => 
                if checkForRocks_(worldSize, nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if currentPositionY - nSteps > 0 then 
                    currentPositionY = currentPositionY - nSteps else currentPositionY = 0
            case Orientation.RIGHT => 
                if checkForRocks_(worldSize, nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if currentPositionX + nSteps < worldSize then 
                currentPositionX = currentPositionX + nSteps else currentPositionX = worldSize-1
            case Orientation.DOWN => 
                if checkForRocks_(worldSize, nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if currentPositionY + nSteps < worldSize then 
                currentPositionY = currentPositionY + nSteps else currentPositionY = worldSize-1
            case Orientation.LEFT => 
                if checkForRocks_(worldSize, nSteps, state) then
                    println("Cant move forward, there is a rock in the way!")
                else if currentPositionX - nSteps > 0 then 
                currentPositionX = currentPositionX - nSteps else currentPositionX = 0
    }

    def checkForRocks_(worldSize: Int, nSteps: Int, state: State): Boolean = {
        var bool = false
        currentOrientation match
            case Orientation.UP => 
                for step <- 1 until nSteps do
                    var nextStep = currentPositionY - step
                    if nextStep < 0 then
                        nextStep = 0
                    var square = state.getSquare(currentPositionX, nextStep)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
            case Orientation.RIGHT => 
                //fertig!!
                var maxSteps = 0
                if currentPositionX + nSteps >= worldSize then
                    maxSteps = currentPositionX
                else 
                    maxSteps = nSteps + 1

                for step <- 1 until maxSteps do
                    
                    var nextStep = currentPositionX + step
                    var square = state.getSquare(nextStep, currentPositionY)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
            case Orientation.DOWN =>
                for step <- 1 until nSteps do
                    var nextStep = currentPositionY + step
                    if nextStep >= worldSize then
                        nextStep = worldSize - 1
                    var square = state.getSquare(currentPositionX, nextStep)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
            case Orientation.LEFT => 
                
                var maxSteps = 0
                if currentPositionX - nSteps >= 0 then
                    maxSteps = nSteps
                else 
                    maxSteps = currentPositionX

                for step <- 1 until maxSteps do
                    
                    var nextStep = currentPositionX - step
                    var square = state.getSquare(nextStep, currentPositionY)
                    if square.isInstanceOf[RockSquare] then
                        bool = true
        
        return bool
        
    }
}

