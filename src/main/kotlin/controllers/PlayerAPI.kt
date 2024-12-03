package org.example.controllers

import org.example.models.Player

class PlayerAPI {
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
}




