package org.example.controllers

import org.example.models.Player
import org.example.models.Team
import persistence.Serializer

class PlayerAPI (serializertype: Serializer){

    private var serializer: Serializer = serializertype
    private var players = ArrayList<Player>()

    fun add(player: Player): Boolean {
        return players.add(player)
    }


    fun listAllPlayers(): String {
        return if (players.isEmpty()) {
            "no players available"
        } else {
            var listofPlayers = ""
            for (i in players.indices) {
                listofPlayers += "${i}: ${players[i]} \n"
            }
            listofPlayers
        }
    }

    fun numberOfPlayers(): Int {
        return players.size
    }

    fun findPlayer(index: Int): Player? {
        return if (isValidListIndex(index, players)) {
            players[index]
        } else null
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun listActivePlayers(): String {
        return if (numberofActivePlayers() == 0) {
            "no active players available"
        } else {
            var listOfActivePlayer = ""
            for (player in players) {
                if (!player.Playerinjury) {
                    listOfActivePlayer += "${players.indexOf(player)}: $player \n"
                }
            }
            listOfActivePlayer
        }
    }

    fun listInjuredPlayers(): String {
        return if (numberOfInjuredPlayers() == 0) {
            "no injured players available"
        } else {
            var listInjuredPlayers = ""
            for (player in players) {
                if (player.Playerinjury) {
                    listInjuredPlayers += "${players.indexOf(player)}: $player \n"
                }
            }
            listInjuredPlayers
        }
    }

    fun listPlayersByKitNumber(number: Int): String {
        return if (players.isEmpty()) {
            "no kit numbers stored"
        } else {
            var listOfPlayers = ""
            for (i in players.indices) {
                if (players[i].Playernumber == number) {
                    listOfPlayers +=
                        """$i: ${players[i]}
                        """.trimIndent()
                }
            }
            if (listOfPlayers == "") {
                "no player with kit number: $number"
            } else {
                "${numberOfPlayersbyKitNumber(number)} kits with kit numbers $number: $listOfPlayers"
            }
        }
    }


    fun numberOfPlayersbyKitNumber(number: Int): Int {
        var counter = 0
        for (player in players) {
            if (player.Playernumber == number) {
                counter++
            }
        }
        return counter
    }

    fun numberOfInjuredPlayers(): Int {
        var counter = 0
        for (player in players) {
            if (!player.Playerinjury) {
                counter++
            }
        }

        return counter
    }

    fun numberofActivePlayers(): Int {
        var counter = 0
        for (player in players) {
            if (!player.Playerinjury) {
                counter++
            }
        }
        return counter
    }

    fun deletePlayer(indexToDelete: Int): Player? {
        return if (isValidListIndex(indexToDelete, players)) {
            players.removeAt(indexToDelete)
        } else null
    }


    fun isValidListIndex(index: Int): Boolean {
        return isValidListIndex(index, players)
    }

    fun updatePlayer(indextoUpdate: Int, player: Player?): Boolean {
        val foundPlayer = findPlayer(indextoUpdate)

        if ((foundPlayer != null) && (player != null)) {
            foundPlayer.Firstname = player.Firstname
            foundPlayer.Lastname = player.Lastname
            foundPlayer.Playerposition = player.Playerposition
            foundPlayer.Playerprice = player.Playerprice
            foundPlayer.Playernumber = player.Playernumber
            return true
        }
        return false


    }

    @Throws(Exception::class)
    fun load() {
        players = serializer.read() as ArrayList<Player>
    }

    @Throws(Exception::class)
    fun store(){
        serializer.write(players)
    }
}








