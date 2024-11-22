package org.example.controllers

import org.example.models.Player

class PlayerAPI {
    private var players = ArrayList<Player>()

    fun add(player: Player): Boolean {
        return players.add(player)
    }

    fun listAllPlayers(): String {
        return if (players.isEmpty()) {
            "No teams availible"
        } else {
            var listofTeams = ""
            for (i in players.indices) {
                listofTeams += "${i}: ${players[i]} \n"
            }
            listofTeams
        }
    }
}


