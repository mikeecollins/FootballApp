package org.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.example.controllers.PlayerAPI
import org.example.controllers.TeamAPI
import org.example.models.Player
import org.example.models.Team
import org.example.utils.readNextInt
import org.example.utils.readNextLine
import java.lang.System.exit



private val logger = KotlinLogging.logger {}
private val teamAPI = TeamAPI()
private val playerAPI = PlayerAPI()

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
          |                                |
          |--------------------------------|
          |  PLAYER MENU                   |
          |--------------------------------|
          |   5) Add a player              |
          |   6) List all players          |
          |   7) Update a players          |
          |   8) Delete a players          |
          |                                |
          ----------------------------------
          |   0) Exit                      |
          ////////////////\\\\\\\\\\\\\\\\\\
          >> """.trimMargin(">"))
    return readNextInt(" > ==>>")
}
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addTeam()
            2  -> listTeam()
            3  -> updateTeam()
            4  -> deleteTeam()
            5  -> addPlayer()
            6  -> listPlayer()
            7  -> updatePlayer()
            8  -> deletePlayer()
            0  -> exitApp()
            else -> println("Invalid option entered: ${option}")
        }
    } while (true)
}
fun addTeam(){

    val Teamdivision = readNextLine("Enter the teams current divsion")
    val Teamsponsor = readNextLine("Enter a sponsorship for a team:")
    val Teamname =  readNextLine("Enter a team name:")
    val Teampoints = readNextInt("Enter points the team have earned this season (Max is 30)")
    val isAdded = teamAPI.add(Team(Teamdivision,Teamsponsor,Teamname,Teampoints,false))

    if (isAdded) {
        println("Added successfully")
    } else{
        println("Add failed")
    }
}

fun addPlayer(){

    val Firstname = readNextLine("Enter the players firtname")
    val Lastname = readNextLine("Enter the players last name")
    val Playernumber = readNextInt("Enter the players kit number")
    val Playerposition = readNextLine("Where does this player play?")
    val isAdded = playerAPI.add(Player(Firstname,Lastname,Playerposition,Playernumber))

    if (isAdded) {
        println("Added successfully")
    } else{
        println("Add failed")
    }
}


fun listTeam(){
    println(teamAPI.listAllTeams())
}

fun updateTeam(){
    logger.info { "updateTeam() function invoked" }
}

fun deleteTeam(){
    logger.info { "deleteTeam() function invoked" }
}

fun listPlayer(){
    println(playerAPI.listAllPlayers())
}
fun updatePlayer(){
    logger.info { "updatePlayer() function invoked" }
}
fun deletePlayer(){
    logger.info { "deletePlayer() function invoked" }
}


fun exitApp(){
    println("Exiting...bye")
    exit(0)
}