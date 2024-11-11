package org.example

import java.lang.System.exit

fun main() {
    runMenu()
}
fun mainMenu() : Int {
    println("")
    println("--------------------")
    println("COLLEGE FOOTBALL APP")
    println("--------------------")
    println("MAIN MENU")
    println("  1) Add a team")
    println("  2) List all teams")
    println("  3) Update a team")
    println("  4) Delete a team")
    println("  5) Have this team won the league?") //true or false question boolean
    println("  6) Add a player")
    println("  7) List all players")
    println("  8) Update a player")
    println("  9) Delete a player")
    println("  10) How many points those the team have")



    println("--------------------")
    println("  0) Exit")
    println("--------------------")
    print("==>> ")
    return readlnOrNull()?.toIntOrNull() ?: -1
}
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addTeam()
            2  -> listTeam()
            3  -> updateTeams()
            4  -> deleteTeams()
            5  -> leagueWon()
            6  -> addPlayer()
            7  -> listPlayer()
            8  -> updatePlayer()
            9  -> deletePlayer()
            10 -> addPoints()
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

fun leagueWon(){
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

fun addPoints(){
    println("You have selected to add points")
}




fun exitApp(){
    println("Exiting...bye")
    exit(0)
}