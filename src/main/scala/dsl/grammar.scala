package scala.dsl

/* 
This is a simple grammar specified for niki the robot:
    program ::= {command} A program consists of multiple commands
    command ::= "turn left;"
                |"turn right;"
                |"move forward" number ";"
                |"pick up;"
                |"drop;"
 */