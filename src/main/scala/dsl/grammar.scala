package scala.dsl

/* 
This is a simple grammar specified for niki the robot:
    program ::= {command} A program consists of multiple commands
    command ::= "turn left;"
                |"turn right;"
                |"move forward" number ";"
                |"pick up;"
                |"drop;"
                | "show if" condition
                | "if" condition "then" program "else" program "end"
                | "while" condition "do" program "end"

    condition ::= "true"
                | "square has items"
                | "robot has items"
                | "is rock ahead"
                | "is edge ahead"
                | "not" condition
                | condition "and" condition
                | condition "or" condition
                | "(" condition ")"
 */