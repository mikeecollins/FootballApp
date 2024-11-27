package controllers


import org.example.controllers.TeamAPI
import org.example.models.Team
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
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

        teamOne = Team("Premier division", "Lidl", "Real Kotlin", 16, false)
        teamTwo = Team("Championship division", "Aldi", "Playboys", 10, false)
        teamThree = Team("Ligue 1", "Tesco", "Intellij aid", 20, false)
        teamFour = Team("SSE Airtricity", "Aer Lingus", "Opium", 33, false)
        teamFive = Team("LaDivision league", "Dairygold", "Mad lads", 16, false)

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

    @Test
    fun `adding a Team to the full team adds to ArrayList`() {
        val newTeam = Team("Browy league", "Fairy", "Full Kits", 12, false)
       assertEquals(5,fullTeams!!.numberOfTeams())
        assertTrue(fullTeams!!.add(newTeam))
        assertEquals(6,fullTeams!!.numberOfTeams())
        assertEquals(newTeam, fullTeams!!.findTeams(fullTeams!!.numberOfTeams()-1))



    }


    @Test
    fun `adding a Team to the no teams list adds to ArrayList`() {
        val newTeam = Team("Browy league", "Fairy", "Full Kits", 12, false)
        assertEquals(0,noTeams!!.numberOfTeams())
        assertTrue(noTeams!!.add(newTeam))
        assertEquals(newTeam,noTeams!!.findTeams(noTeams!!.numberOfTeams()-1))

    }
}