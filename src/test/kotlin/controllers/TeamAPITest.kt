package controllers


import org.example.controllers.PlayerAPI
import org.example.controllers.TeamAPI
import org.example.models.Team
import org.example.persistence.XMLSerializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
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
    private var fullTeams: TeamAPI? = TeamAPI(XMLSerializer(File("teams.xml")))
    private var noTeams: TeamAPI? = TeamAPI(XMLSerializer(File("teams.xml")))

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
            assertEquals(newTeam, fullTeams!!.findTeam(fullTeams!!.numberOfTeams() - 1))


        }


        @Test
        fun `adding a Team to the no teams list adds to ArrayList`() {
            val newTeam = Team("Browy league", "Fairy", "Full Kits", 12, 6, false)
            assertEquals(0, noTeams!!.numberOfTeams())
            assertTrue(noTeams!!.add(newTeam))
            assertEquals(newTeam, noTeams!!.findTeam(noTeams!!.numberOfTeams() - 1))

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
                assertEquals(3, fullTeams!!.numberOfActiveTeams())
                val activeTeamsString = fullTeams!!.listActiveTeams().lowercase()
                assertTrue(activeTeamsString.contains("real kotlin"))
                assertFalse(activeTeamsString.contains("playboys"))
                assertTrue(activeTeamsString.contains("intellij aid"))
                assertTrue(activeTeamsString.contains("opium"))
                assertFalse(activeTeamsString.contains("mad lads"))
            }

            @Test
            fun `listTrophyTeams returns no winning teams when ArrayList is empty`() {
                assertEquals(0, noTeams!!.numberOfWinningTeams())
                assertTrue(noTeams!!.listTrophyTeams().lowercase().contains("no title winning team available"))
            }

            @Test
            fun `listTrophyTeams returns winning teams when ArrayList has winning teams stored`() {
                assertEquals(3, fullTeams!!.numberOfWinningTeams())
                val winningTeamsString = fullTeams!!.listTrophyTeams().lowercase(Locale.getDefault())
                assertFalse(winningTeamsString.contains("real kotlin"))
                assertTrue(winningTeamsString.contains("playboys"))
                assertFalse(winningTeamsString.contains("intellij aid"))
                assertFalse(winningTeamsString.contains("opium"))
                assertTrue(winningTeamsString.contains("mad lads"))
            }

            @Test
            fun `listTeamByLeague returns No Teams when Array list is empty`() {
                assertEquals(0, noTeams!!.numberOfTeams())
                assertTrue(noTeams!!.listTeamByLeague(1).lowercase().contains("no teams"))
            }

            @Test
            fun `listTeamByLeague returns no teams of that kit number exist`() {
                assertEquals(5, fullTeams!!.numberOfTeams())
                val teamnumber5string = fullTeams!!.listTeamByLeague(1).lowercase()
                // assertTrue(teamnumber5string.contains("no teams available"))
                assertTrue(teamnumber5string.contains("1"))
            }

            @Test
            fun `listTeamByLeague returns all teams that match position when teams of that position exist`() {
                assertEquals(5, fullTeams!!.numberOfTeams())
                val teamnumber1string = fullTeams!!.listTeamByLeague(1).lowercase()
                assertTrue(teamnumber1string.contains("1"))
                assertTrue(teamnumber1string.contains("playboys"))

                val teamnumber2string = fullTeams!!.listTeamByLeague(2).lowercase()
                assertTrue(teamnumber2string.contains("2"))
                assertTrue(teamnumber2string.contains("real kotlin"))

                val teamnumber3string = fullTeams!!.listTeamByLeague(3).lowercase()
                assertTrue(teamnumber3string.contains("3"))
                assertTrue(teamnumber3string.contains("intellij aid"))

                val teamnumber4string = fullTeams!!.listTeamByLeague(4).lowercase()
                assertTrue(teamnumber4string.contains("4"))
                assertTrue(teamnumber4string.contains("opium"))

                val teamnumber6string = fullTeams!!.listTeamByLeague(5).lowercase()
                assertTrue(teamnumber6string.contains("5"))
                assertTrue(teamnumber6string.contains("mad lads"))


            }

            @Nested
            inner class DeleteTeams {

                @Test
                fun `deleting a Team that does not exist, returns null`() {
                    assertNull(noTeams!!.deleteTeam(-0))
                    assertNull(noTeams!!.deleteTeam(-1))
                    assertNull(noTeams!!.deleteTeam(5))
                }

                @Test
                fun `deleting a team that exist delete and returns deleted object`() {
                    assertEquals(5, fullTeams!!.numberOfTeams())
                    assertEquals(teamFive, fullTeams!!.deleteTeam(4))
                    assertEquals(4, fullTeams!!.numberOfTeams())
                    assertEquals(teamOne, fullTeams!!.deleteTeam(0))
                    assertEquals(3, fullTeams!!.numberOfTeams())
                }

                @Nested
                inner class UpdateTeam

                @Test
                fun `updating a team that does not exist returns false`() {
                    assertFalse(fullTeams!!.updateTeam(6, Team("La Liga", "Fly Emirates", "Girona", 28, 10, false)))
                    assertFalse(
                        fullTeams!!.updateTeam(
                            -1,
                            Team("Premier division", "Shiki Sushi", "Bohs FC", 25, 7, false)
                        )
                    )
                    assertFalse(noTeams!!.updateTeam(0, Team("First league", "Aldi", "Gutters", 8, 20, false)))
                }

                @Test
                fun `updating a team that exists returns true and updates`() {
                    assertEquals(teamFive, fullTeams!!.findTeam(4))
                    assertEquals("Mad Lads", fullTeams!!.findTeam(4)!!.Teamname)
                    assertEquals(5, fullTeams!!.findTeam(4)!!.Teamposition)
                    assertEquals("Dairygold", fullTeams!!.findTeam(4)!!.Teamsponsor)
                    assertEquals(16, fullTeams!!.findTeam(4)!!.Teampoints)
                    assertEquals("LaDivision league", fullTeams!!.findTeam(4)!!.Teamdivision)

                    assertTrue(fullTeams!!.updateTeam(4, Team("Champs Div", "Londais", "Crystal", 17, 9, false)))
                    assertEquals("Champs Div", fullTeams!!.findTeam(4)!!.Teamdivision)
                    assertEquals("Londais", fullTeams!!.findTeam(4)!!.Teamsponsor)
                    assertEquals("Crystal", fullTeams!!.findTeam(4)!!.Teamname)
                    assertEquals(17, fullTeams!!.findTeam(4)!!.Teampoints)
                    assertEquals(9, fullTeams!!.findTeam(4)!!.Teamposition)
                }

                @Nested
                inner class PersistenceTests {

                    @Test
                    fun ` saving and loading an empty collection in XML does not crash app`() {
                        val storingTeams = TeamAPI(XMLSerializer(File("teams.xml")))
                        storingTeams.store()

                        val loadedTeams = TeamAPI(XMLSerializer(File("teams.xml")))
                        loadedTeams.load()

                        assertEquals(0, storingTeams.numberOfTeams())
                        assertEquals(0, loadedTeams.numberOfTeams())
                        assertEquals(storingTeams.numberOfTeams(),loadedTeams.numberOfTeams())

                    }

                    @Test
                    fun `saving and loading an loaded collection in XML doesn't loose data`() {
                        val storingTeams = TeamAPI(XMLSerializer(File("teams.xml")))
                        storingTeams.add(teamOne!!)
                        storingTeams.add(teamTwo!!)
                        storingTeams.add(teamThree!!)
                        storingTeams.add(teamFour!!)
                        storingTeams.add(teamFive!!)
                        storingTeams.store()

                        val loadedTeams = TeamAPI(XMLSerializer(File("teams.xml")))
                        loadedTeams.load()

                        assertEquals(5, storingTeams.numberOfTeams())
                        assertEquals(5, storingTeams.numberOfTeams())
                        assertEquals(storingTeams.numberOfTeams(),loadedTeams.numberOfTeams())
                        assertEquals(storingTeams.findTeam(0),loadedTeams.findTeam(0))
                        assertEquals(storingTeams.findTeam(1),loadedTeams.findTeam(1))
                        assertEquals(storingTeams.findTeam(2),loadedTeams.findTeam(2))
                    }
                }
            }
        }
    }
}