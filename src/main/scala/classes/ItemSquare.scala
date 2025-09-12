package classes

import classes.Item
import interfaces.Square

class ItemSquare(val content: Item) extends Square {
    def getItem(): Item = {
        return this.content
    }
}
