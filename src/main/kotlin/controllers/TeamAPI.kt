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

    fun findTeam(index: Int): Team? {
        return if (isValidListIndex(index, teams)) {
            teams[index]
        } else null
    }


    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun listActiveTeams(): String {
        return if (numberOfActiveTeams() == 0) {
            "no active teams available"
        } else {
            var listOfActiveTeam = ""
            for (team in teams) {
                if (!team.isTitleWon) {
                    listOfActiveTeam += "${teams.indexOf(team)}: $team \n"
                }
            }
            listOfActiveTeam
        }


    }
    fun listTrophyTeams(): String {
        return if (numberOfWinningTeams() == 0) {
            "no title winning team available"
        } else {
            var listTrophyTeam = ""
            for (team in teams) {
                if (team.isTitleWon) {
                    listTrophyTeam += "${teams.indexOf(team)}: $team \n"
                }
            }
            listTrophyTeam
        }
    }

    fun listTeamByLeague(position: Int): String {
        return if (teams.isEmpty()) {
            "no teams available"
        } else {
            var listOfTeams = ""
            for (i in teams.indices) {
                if (teams[i].Teamposition == position) {
                    listOfTeams +=
                       """"$i: ${ teams[i] }
                    """.trimIndent()

                }

            }
            if (listOfTeams == "") {
                "no teams with position: $position"
            } else {
                "${numberOfTeamsByPosition(position)}  teams with position: $position: $listOfTeams"

            }

        }
    }

    fun numberOfTeamsByPosition(position: Int): Int {
        var counter = 0
        for (team in teams) {
            if (team.Teamposition == position) {
                counter++
            }
        }
        return counter
    }

    fun numberOfActiveTeams(): Int {
        var counter = 0
        for (team in teams) {
            if (!team.isTitleWon) {
                counter++
            }
        }

        return counter
    }

    fun numberOfWinningTeams(): Int{
        var counter = 0
        for(team in teams){
            if (!team.isTitleWon) {
                counter++
            }
        }
        return counter
    }

    fun deleteTeam(indexToDelete: Int):Team? {
        return if(isValidListIndex(indexToDelete, teams)) {
            teams.removeAt(indexToDelete)
        } else null
    }

    fun updateTeam(indexToUpdate: Int, team: Team?):Boolean {
        val foundTeam = findTeam(indexToUpdate)

        if ((foundTeam != null) && (team != null)) {
            foundTeam.Teamname = team.Teamname
            foundTeam.Teamdivision = team.Teamdivision
            foundTeam.Teamposition = team.Teamposition
            foundTeam.Teamsponsor = team.Teamsponsor
            foundTeam.Teampoints = team.Teampoints
            return true
        }

        return false
    }

    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, teams)
    }
}








