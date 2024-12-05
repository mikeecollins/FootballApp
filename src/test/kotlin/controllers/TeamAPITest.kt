package controllers


import org.example.controllers.TeamAPI
import org.example.models.Team
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TeamAPITest {

    private var teamOne: Team? = null
    private var teamTwo: Team? = null
    private var teamThree: Team? = null
    private var teamFour: Team? = null
    private var teamFive: Team? = null
    private var fullTeams: TeamAPI? = TeamAPI()
    private var noTeams: TeamAPI? = TeamAPI()

    @BeforeEach
    fun setup() {

        teamOne = Team("Premier division", "Lidl", "Real Kotlin", 16, 2, false)
        teamTwo = Team("Championship division", "Aldi", "Playboys", 10, 1, true)
        teamThree = Team("Ligue 1", "Tesco", "Intellij aid", 20, 3, false)
        teamFour = Team("SSE Airtricity", "Aer Lingus", "Opium", 33, 4, false)
        teamFive = Team("LaDivision league", "Dairygold", "Mad Lads", 16, 5, true)

        fullTeams!!.add(teamOne!!)
        fullTeams!!.add(teamTwo!!)
        fullTeams!!.add(teamThree!!)
        fullTeams!!.add(teamFour!!)
        fullTeams!!.add(teamFive!!)

    }

    @AfterEach
    fun tearDown() {
        teamOne = null
        teamTwo = null
        teamThree = null
        teamFour = null
        teamFive = null
        fullTeams = null
        noTeams = null

    }


    @Nested
    inner class AddTeams {


        @Test
        fun `adding a Team to the full team adds to ArrayList`() {
            val newTeam = Team("Browy league", "Fairy", "Full Kits", 12, 6, false)
            assertEquals(5, fullTeams!!.numberOfTeams())
            assertTrue(fullTeams!!.add(newTeam))
            assertEquals(6, fullTeams!!.numberOfTeams())
            assertEquals(newTeam, fullTeams!!.findTeams(fullTeams!!.numberOfTeams() - 1))


        }


        @Test
        fun `adding a Team to the no teams list adds to ArrayList`() {
            val newTeam = Team("Browy league", "Fairy", "Full Kits", 12, 6, false)
            assertEquals(0, noTeams!!.numberOfTeams())
            assertTrue(noTeams!!.add(newTeam))
            assertEquals(newTeam, noTeams!!.findTeams(noTeams!!.numberOfTeams() - 1))

        }


        @Nested
        inner class ListTeams {


            @Test
            fun `listAllTeams returns No Teams Available message when ArrayList is empty`() {
                assertEquals(0, noTeams!!.numberOfTeams())
                assertTrue(noTeams!!.listAllTeams().lowercase().contains("no teams available"))
            }

            @Test
            fun `listAllTeams returns Teams when ArrayList has teams stored`() {
                assertEquals(5, fullTeams!!.numberOfTeams())
                val teamsString = fullTeams!!.listAllTeams().lowercase()
                assertTrue(teamsString.contains("real kotlin"))
                assertTrue(teamsString.contains("playboys"))
                assertTrue(teamsString.contains("intellij aid"))
                assertTrue(teamsString.contains("opium"))
                assertTrue(teamsString.contains("mad lads"))
            }

            @Test
            fun `listActiveTeams returns no active teams stored when ArrayList is empty`() {
                assertEquals(0, noTeams!!.numberOfActiveTeams())
                assertTrue(noTeams!!.listActiveTeams().lowercase().contains("no active teams available"))
            }

            @Test
            fun `listActiveTeams returns active teams when ArrayList has active teams stored`() {
                assertEquals(3,fullTeams!!.numberOfActiveTeams())
                val activeTeamsString = fullTeams!!.listActiveTeams().lowercase()
                assertTrue(activeTeamsString.contains("real kotlin"))
                assertFalse(activeTeamsString.contains("playboys"))
                assertTrue(activeTeamsString.contains("intellij aid"))
                assertTrue(activeTeamsString.contains("opium"))
                assertFalse(activeTeamsString.contains("mad lads"))
            }

            @Test
            fun`listTrophyTeams returns no winning teams when ArrayList is empty`(){
                assertEquals(0, noTeams!!.numberOfWinningTeams())
                assertTrue(noTeams!!.listTrophyTeams().lowercase().contains("no title winning team available"))
            }

            @Test
            fun`listTrophyTeams returns winning teams when ArrayList has winning teams stored`(){
               assertEquals(3,fullTeams!!.numberOfWinningTeams())
                val winningTeamsString = fullTeams!!.listTrophyTeams().lowercase(Locale.getDefault())
                assertFalse(winningTeamsString.contains("real kotlin"))
                assertTrue(winningTeamsString.contains("playboys"))
                assertFalse(winningTeamsString.contains("intellij aid"))
                assertFalse(winningTeamsString.contains("opium"))
                assertTrue(winningTeamsString.contains("mad lads"))
            }
        }
    }
}
