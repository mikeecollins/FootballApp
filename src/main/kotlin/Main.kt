package org.example

import io.github.oshai.kotlinlogging.KotlinLogging
import org.example.utils.readNextInt
import java.lang.System.exit



private val logger = KotlinLogging.logger {}

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
            5  -> trophyWon()
            6  -> addPlayer()
            7  -> listPlayer()
            8  -> updatePlayer()
            9  -> deletePlayer()
            10 -> kitNumber()
            0  -> exitApp()
            else -> println("Invalid option entered: ${option}")
        }
    } while (true)
}
fun addTeam(){
    logger.info { "addTeam() function invoked" }
}

fun listTeam(){
    logger.info { "listTeam() function invoked" }
}

fun updateTeam(){
    logger.info { "updateTeam() function invoked" }
}

fun deleteTeam(){
    logger.info { "deleteTeam() function invoked" }
}

fun trophyWon(){
    logger.info { "trophyWon() function invoked" }
}

fun addPlayer(){
    logger.info { "addPlayer() function invoked" }
}

fun listPlayer(){
    logger.info { "listPlayer() function invoked" }
}
fun updatePlayer(){
    logger.info { "updatePlayer() function invoked" }
}
fun deletePlayer(){
    logger.info { "deletePlayer() function invoked" }
}

fun kitNumber(){
    logger.info { "kitNumber() function invoked" }
}




fun exitApp(){
    println("Exiting...bye")
    exit(0)
}