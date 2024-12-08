package org.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.example.controllers.PlayerAPI
import org.example.controllers.TeamAPI
import org.example.models.Player
import org.example.models.Team
import org.example.persistence.XMLSerializer
import org.example.utils.readNextDouble
import org.example.utils.readNextInt
import org.example.utils.readNextLine
import java.io.File
import java.lang.System.exit



private val logger = KotlinLogging.logger {}
private val teamAPI = TeamAPI(XMLSerializer(File("teams.xml")))
private val playerAPI = PlayerAPI(XMLSerializer(File("players.xml")))

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
    val Teamposition = readNextInt("Enter a team position:")
    val isAdded = teamAPI.add(Team(Teamdivision,Teamsponsor,Teamname,Teampoints,Teamposition,false))

    if (isAdded) {
        println("Added successfully")
    } else{
        println("Add failed")
    }
}

fun addPlayer(){

    val Firstname = readNextLine("Enter the players first name")
    val Lastname = readNextLine("Enter the players last name")
    val  Playerposition= readNextLine("Which posistion does this player play in")
    val  Playerprice= readNextDouble(" Enter the players price")
    val Playernumber = readNextInt("Enter the players kit number")
    val isAdded = playerAPI.add(Player(Firstname,Lastname,Playerposition,Playerprice,Playernumber,false))


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
    listTeam()
    if (teamAPI.numberOfTeams() > 0 ){
        val indexToUpdate = readNextInt("Enter the index of the team you would like to update")
        if (teamAPI.isValidIndex(indexToUpdate)) {
            val Teamdivision = readNextLine("enter the teams current division")
            val Teamsponsor = readNextLine("enter the teams sponsorship")
            val Teamname = readNextLine("Enter the team name")
            val Teampoints = readNextInt("Enter the teams points")
            val Teamposition = readNextInt("Enter the team current position")

            if (teamAPI.updateTeam(indexToUpdate, Team(Teamdivision,Teamsponsor,Teamname,Teamposition,Teampoints,false)))
                println("Updated successfully")
        } else {
            println("Update failed")
        }
    } else {
        println("Index does not exist for this team")
    }
}

fun deleteTeam(){
  listTeam()
    if (teamAPI.numberOfTeams()>0){
        val indexToDelete = readNextInt("Enter the team name you want to delete:")
        val teamToDelete = teamAPI.deleteTeam(indexToDelete)
        if (teamToDelete != null) {
            println("Deleted successfully Deleted team: ${teamToDelete.Teamname}")
        } else {
            println("Deleted failed")
        }
    }
}

fun listPlayer(){
    println(playerAPI.listAllPlayers())
}
fun updatePlayer(){
   listPlayer()
    if (playerAPI.numberOfPlayers() > 0){
        val indexToUpdate = readNextInt("Enter the index of the player to update:")
        if (playerAPI.isValidListIndex(indexToUpdate)){
            val Firstname = readNextLine("Enter the first name of the player")
            val Lastname = readNextLine("Enter the last name of the player")
            val Playerposition = readNextLine("Enter the player position")
            val Playerprice = readNextDouble("Enter the players price")
            val Playernumber = readNextInt("Enter the players kit number")

            if (playerAPI.updatePlayer(indexToUpdate, Player(Firstname,Lastname,Playerposition,Playerprice,Playernumber,false))){
                println("Updated")
                } else {
                    println("Updated failed")
                }
         } else {
             println("Index does not exist for this player")
        }
    }
}
fun deletePlayer(){
    listPlayer()
    if (playerAPI.numberOfPlayers()>0) {
        val indexToDelete = readNextInt("Enter the player you want to delete:")
        val playerToDelete = playerAPI.deletePlayer(indexToDelete)
        if (playerToDelete != null) {
            println("Player Deleted! Deleted player: ${playerToDelete.Firstname}")
        } else {
            println("Error Deleting Player")
        }

        fun savePlayer() {
            try {
                playerAPI.store()

            } catch (e: Exception) {
                System.err.println("Error writing to file: $e")
            }
                    
        }
        fun loadPlayer(){
            try {
                playerAPI.load()
            }catch (e: Exception){
                System.err.println("Error reading file: $e")
            }
        }

        fun saveTeam(){
            try {
                teamAPI.store()
            } catch (e: Exception) {
                System.err.println("Error writing to file: $e")
            }
        }
        fun loadTeam(){
            try {
                teamAPI.load()
            }catch (e: Exception){
                System.err.println("Error writing file: $e")
            }
        }

    }
}


fun exitApp(){
    println("Exiting...bye")
    exit(0)
}