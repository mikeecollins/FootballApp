package org.example.controllers

import org.example.models.Player
import org.example.models.Team

class TeamAPI {
    private var teams = ArrayList<Team>()

    fun add(team: Team): Boolean {
        return teams.add(team)
    }


    fun listAllTeams(): String {
        return if (teams.isEmpty()) {
            "no teams available"
        } else {
            var listofTeams = ""
            for (i in teams.indices) {
                listofTeams += "${i}: ${teams[i]} \n"
            }
            listofTeams
        }
    }

    fun numberOfTeams(): Int {
        return teams.size
    }

    fun findTeams(index: Int): Team? {
        return if (isValidListIndex(index, teams)) {
            teams[index]
        } else null
    }


    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}






