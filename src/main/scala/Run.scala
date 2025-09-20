package scala 

import worldLogic.classes.Program

object Run {
    //example interaction
    @main def main(): Unit = {
        val world = Program(worldSize = 5)
        
        world.visualzeStart()
        world.move(3)
        world.turnRight()
        world.move(4)
        world.tryPickUp()
        world.turnLeft()
        world.move(4)
        world.tryDrop()
        world.turnRight()
        world.move(4)
    }
}