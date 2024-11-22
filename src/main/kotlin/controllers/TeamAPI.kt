package org.example.controllers

import org.example.models.Team

class TeamAPI {
    private var teams = ArrayList<Team>()

    fun add(team: Team): Boolean {
        return teams.add(team)
    }


    fun listAllTeams(): String {
        return if (teams.isEmpty()) {
            "No teams availible"
        } else {
            var listofTeams = ""
            for (i in teams.indices) {
                listofTeams += "${i}: ${teams[i]} \n"
            }
            listofTeams
        }
    }
}






