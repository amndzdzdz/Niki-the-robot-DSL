# Niki the Robot – Game & Domain-Specific Language  

In this project I implemented a simple grid-based game called Niki the Robot, along with a custom domain-specific language (DSL) to control the robot.  

---

## About the Game  
Niki is a controllable robot that can:  
- Walk around in a grid world  
- Pick up and drop items  
- Avoid rocks and obstacles  

The grid world and its rules are modeled through a clean domain model in Scala and are visualized in the terminal through emojis.  

---

## About the DSL  
To interact with the game, a domain-specific language has been created. It includes:  
- A grammar definition  
- A parser for processing the grammar  
- An abstract syntax representation  
- An interpreter for executing DSL commands  

---

## Project Structure  

├── interactions/               # Example DSL scripts
│   ├── forward.niki
│   ├── pickup.niki
│   ├── show.niki
│   └── step.niki
│
├── src/main/scala/
│   ├── dsl/                    # DSL implementation
│   │   ├── AbstractSyntax.scala
│   │   ├── Grammar.scala
│   │   ├── Interpreter.scala
│   │   └── Parser.scala
│   │
│   ├── worldLogic/             # Game domain model
│   │   ├── classes/
│   │   │   ├── EmptySquare.scala
│   │   │   ├── GameWorld.scala
│   │   │   ├── Item.scala
│   │   │   ├── ItemSquare.scala
│   │   │   ├── Robot.scala
│   │   │   ├── RockSquare.scala
│   │   │   └── State.scala
│   │   ├── enums/
│   │   │   └── Orientation.scala
│   │   └── interfaces/
│   │       └── Square.scala
│   │
│   └── Run.scala               # Entry point
│
├── build.sbt
└── readme.md

---

## Installation  

1. Clone the repository:  
   `git clone https://github.com/amndzdzdz/Niki-the-robot-DSL.git`
   `cd Niki-the-robot-DSL`
   
2. Make sure you have Scala and sbt installed
3. Build the project:
   `sbt compile`
4. You can run the main file via:
   `scala run Run.scala`

## Usage Example

You can write your own programs for Niki using the provided grammar.

- Programs are written in `.niki` text files.  
- Example scripts are included in the `interactions/` folder (`forward.niki`, `pickup.niki`, `show.niki`, `step.niki`).  
- To run your own program:

  1. Create a `.niki` file with your commands (use the grammar in `dsl/Grammar.scala` as a reference).  
  2. Update the file path inside `Run.scala` to point to your `.niki` file.  
  3. Run the project

This lets you control the robot using small custom scripts similar to those in the `interactions/` folder.  

---

## License

This project is licensed under the MIT License.  
You are free to use, modify, and distribute this software with attribution.  
