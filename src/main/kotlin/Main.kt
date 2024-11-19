package org.example

import java.lang.System.exit

fun main() {
    runMenu()
}
fun mainMenu(): Int {
    print("""
          ////////////////\\\\\\\\\\\\\\\\\\
          |       COLLEGE FOOTBALL APP     |
          ----------------------------------
          |  TEAM MENU                     |
          |--------------------------------|
          |   1) Add a team                |
          |   2) List all teams            |
          |   3) Update a team             |
          |   4) Delete a team             |
          |   5) How many trophies won     |
          |--------------------------------|
          |  PLAYER MENU                   |
          |--------------------------------|
          |   6) Add a player              |
          |   7) List all players          |
          |   8) Update a players          |
          |   9) Delete a players          |
          |   10) Kit numbers              |
          ----------------------------------
          |   0) Exit                      |
          ////////////////\\\\\\\\\\\\\\\\\\
          ==>> """.trimMargin(">"))
    return readln().toInt()
}
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addTeam()
            2  -> listTeam()
            3  -> updateTeams()
            4  -> deleteTeams()
            5  -> trophyWon()
            6  -> addPlayer()
            7  -> listPlayer()
            8  -> updatePlayer()
            9  -> deletePlayer()
            10 -> kitNumbers()
            0  -> exitApp()
            else -> println("Invalid option entered: " + option)
        }
    } while (true)
}
fun addTeam(){
    println("You have selected to add a team")
}

fun listTeam(){
    println("You have selected to list a team")
}

fun updateTeams(){
    println("You have selected to update a team")
}

fun deleteTeams(){
    println("You have selected to delete a team")
}

fun trophyWon(){
    println("You have selected to see if a team has won the league")
}

fun addPlayer(){
    println("You have selected to add a player")
}

fun listPlayer(){
    println("You have selected to list a player")
}
fun updatePlayer(){
    println("You have selected to update a player")
}
fun deletePlayer(){
    println("You have selected to delete a player")
}

fun kitNumbers(){
    println("You have selected to add points")
}




fun exitApp(){
    println("Exiting...bye")
    exit(0)
}