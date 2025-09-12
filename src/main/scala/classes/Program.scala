package classes

import classes.State
import scala.compiletime.ops.double
import scala.util.Random
import interfaces.Square
import classes.EmptySquare
import scala.collection.mutable.ArrayBuffer

class Program(worldSize: Int) {

    private val state: State = generateRandomState_()
    private val robot: Robot = Robot(worldSize)
    
    def getState() = {
        state
    }

    def getRobot() = {
        robot
    }

    def generateRandomState_(): State = {
        var state = new ArrayBuffer[ArrayBuffer[Square]]
        var id = 0
        for row <- 0 until worldSize do
            var newRow = new ArrayBuffer[Square]
            for value <- 0 until worldSize do
                var square = sampleSquare_(maxNumberItems = 10)
                newRow.addOne(square)
            state.addOne(newRow)

        return State(state)
    } 

    def sampleSquare_(maxNumberItems: Int): Square = {
        //If returned value is between 0 and 0.6 we will return an EmptySquare Object
        //If returned value is between 0.6 and 0.8 we will return a RockSquare Object
        //If returned value is between 0.8 and 1.0 we will return a ItemSquare Object
        val p = scala.util.Random.nextDouble

        if p < 0.7 then
            return EmptySquare()
        else if p > 0.7 && p < 0.8 then
            return RockSquare()
        else
            val items = sampleItems_(maxNumberItems)

            return ItemSquare(items)
    }

    def sampleItems_(maxNumberItems: Int): ArrayBuffer[Item] = {
        var items = new ArrayBuffer[Item]
        for 
            number <- 0 until maxNumberItems 
        do 
            val p = scala.util.Random.nextDouble
            if p > 0.5 then
                items.addOne(Item())
            
        if items.length == 0 then
            items.addOne(Item())
        
        items
    }
}
