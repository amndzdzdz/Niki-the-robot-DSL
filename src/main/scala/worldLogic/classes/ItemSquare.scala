package worldLogic.classes

import worldLogic.classes.Item
import worldLogic.interfaces.Square

class ItemSquare(val content: Item) extends Square {
    def getItem(): Item = {
        return this.content
    }
}
