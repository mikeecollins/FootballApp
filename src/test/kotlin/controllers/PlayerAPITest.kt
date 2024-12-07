package controllers

import org.example.controllers.PlayerAPI
import org.example.models.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PlayerAPITest {

    private var playerOne: Player? = null
    private var playerTwo: Player? = null
    private var playerThree: Player? = null
    private var playerFour: Player? = null
    private var playerFive: Player? = null
    private var fullList: PlayerAPI? = PlayerAPI()
    private var noPlayers: PlayerAPI? = PlayerAPI()


    @BeforeEach
    fun setup() {
        playerOne = Player("Ebidimo", "Collins", "Left wing/Striker", 100.00, 7, false)
        playerTwo = Player("Zach", "Power", "Left Back", 150.00, 4, true)
        playerThree = Player("Bold", "John", "Striker", 200.00, 9, false)
        playerFour = Player("Anuj", "Yaduv", "Right Wing", 1000.00, 11, false)
        playerFive = Player("Chris", "Yasen", "Center Defensive Midfielder", 2000.00, 6, true)

        fullList!!.add(playerOne!!)
        fullList!!.add(playerTwo!!)
        fullList!!.add(playerThree!!)
        fullList!!.add(playerFour!!)
        fullList!!.add(playerFive!!)
    }

    @AfterEach
    fun tearDown() {
        playerOne = null
        playerTwo = null
        playerThree = null
        playerFour = null
        playerFive = null
        fullList = null
        noPlayers = null
    }

    @Nested
    inner class AddPlayer {


        @Test
        fun `adding a player to the full list adds to the ArrayList`() {
            val newPlayer = Player("Sean", "Purcell", "Right Back", 500.00, 3, false)
            assertEquals(5, fullList!!.numberOfPlayers())
            assertTrue(fullList!!.add(newPlayer))
            assertEquals(6, fullList!!.numberOfPlayers())
            assertEquals(newPlayer, fullList!!.findPlayer(fullList!!.numberOfPlayers() - 1))
        }

        @Test
        fun `adding a player to the no Players list adds to the ArrayList`() {
            val newPlayer = Player("Sean", "Purcell", "Right Back", 500.00, 3, false)
            assertEquals(0, noPlayers!!.numberOfPlayers())
            assertTrue(noPlayers!!.add(newPlayer))
            assertEquals(1, noPlayers!!.numberOfPlayers())
            assertEquals(newPlayer, noPlayers!!.findPlayer(noPlayers!!.numberOfPlayers() - 1))
        }

        @Nested
        inner class ListPlayer {


            @Test
            fun `listAllPlayers returns No Players Available message when ArrayList is empty`() {
                assertEquals(0, noPlayers!!.numberOfPlayers())
                assertTrue(noPlayers!!.listAllPlayers().lowercase().contains("no players available"))
            }

            @Test
            fun `listAllPlayers returns Players when ArrayList has players stored`() {
                assertEquals(5, fullList!!.numberOfPlayers())
                val playersString = fullList!!.listAllPlayers().lowercase()
                assertTrue(playersString.contains("ebidimo"))
                assertTrue(playersString.contains("zach"))
                assertTrue(playersString.contains("bold"))
                assertTrue(playersString.contains("anuj"))
                assertTrue(playersString.contains("chris"))
            }

            @Test
            fun `listActivePlayers returns no active players stored when ArrayList is empty`() {
                assertEquals(0, noPlayers!!.numberofActivePlayers())
                assertTrue(noPlayers!!.listActivePlayers().lowercase().contains("no active players available"))
            }

            @Test
            fun `listActivePlayers returns active players when ArrayList has active players stored`() {
                assertEquals(3, fullList!!.numberofActivePlayers())
                val activePlayersString = fullList!!.listActivePlayers().lowercase()
                assertTrue(activePlayersString.contains("bold"))
                assertFalse(activePlayersString.contains("zach"))
                assertTrue(activePlayersString.contains("ebidimo"))
                assertTrue(activePlayersString.contains("anuj"))
                assertFalse(activePlayersString.contains("chris"))
            }

            @Test
            fun `listInjuredPlayers returns no injured players when ArrayList is empty`() {
                assertEquals(0, noPlayers!!.numberOfInjuredPlayers())
                assertTrue(noPlayers!!.listInjuredPlayers().lowercase().contains("no injured players available"))
            }

            @Test
            fun `listInjuredPlayers returns injured players when ArrayList has injured players stored`() {
                assertEquals(3, fullList!!.numberOfInjuredPlayers())
                val injuredPlayersString = fullList!!.listInjuredPlayers().lowercase(Locale.getDefault())
                assertFalse(injuredPlayersString.contains("bold"))
                assertTrue(injuredPlayersString.contains("zach"))
                assertFalse(injuredPlayersString.contains("ebidimo"))
                assertFalse(injuredPlayersString.contains("anuj"))
                assertTrue(injuredPlayersString.contains("chris"))
            }

            @Test
            fun `listPlayersByKitNumber returns No Players when Array list is empty`(){
                assertEquals(0, noPlayers!!.numberOfPlayers())
                assertTrue(noPlayers!!.listPlayersByKitNumber(1).lowercase().contains("no kit numbers stored"))

            }
            @Test
            fun `listPlayersByKitNumber returns no players of that kit number exist`(){
                assertEquals(5, fullList!!.numberOfPlayers())
                val kitnumber6String = fullList!!.listPlayersByKitNumber(2).lowercase()
                assertTrue(kitnumber6String.contains("no"))
                assertTrue(kitnumber6String.contains("2"))

            }
            @Test
            fun `listPlayersByKitNumber returns all players that match kit number when players of that number exist`(){
                assertEquals(5, fullList!!.numberOfPlayers())
                val kitnumber6String = fullList!!.listPlayersByKitNumber(7).lowercase()
                assertTrue(kitnumber6String.contains("7"))
                assertTrue(kitnumber6String.contains("ebidimo"))

                val kitnumber11String = fullList!!.listPlayersByKitNumber(4).lowercase()
                assertTrue(kitnumber11String.contains("zach"))

                val kitnumber12String = fullList!!.listPlayersByKitNumber(11).lowercase()
                assertTrue(kitnumber12String.contains("anuj"))

                val kitnumber13String = fullList!!.listPlayersByKitNumber(9).lowercase()
                assertTrue(kitnumber13String.contains("bold"))

                val kitnumber3String = fullList!!.listPlayersByKitNumber(6).lowercase()
                assertTrue(kitnumber3String.contains("chris"))
            }

            @Nested
            inner class DeletePlayers

            @Test
            fun`deleting a Player that does not exist, return null`(){
                assertNull(noPlayers!!.deletePlayer(0))
                assertNull(noPlayers!!.deletePlayer(-1))
                assertNull(noPlayers!!.deletePlayer(5))
            }

            @Test
            fun `deleting a player that exists delete and return deleted object`(){
                assertEquals(5, fullList!!.numberOfPlayers())
                assertEquals(playerOne, fullList!!.deletePlayer(0))
                assertEquals(4, fullList!!.numberOfPlayers())
                assertEquals(playerFour, fullList!!.deletePlayer(2))
                assertEquals(3, fullList!!.numberOfPlayers())
            }

            @Nested
            inner class UpdatePlayer{
            @Test
            fun `updating a player that does not exist returns false `(){
                assertFalse(fullList!!.updatePlayer(6,Player("Mike","Collins","Center Midfield,",900.00,92,false)))
                assertFalse(fullList!!.updatePlayer(-1, Player("Sam","Duggan","Center Attacking Mid",1000.00,10,false)))
                assertFalse(noPlayers!!.updatePlayer(0,Player("Josh","Fenton","Right wing",1050.00,48,false)))
            }

                @Test
                fun `updating a player that exist returns true `(){
                    assertEquals(playerFive,fullList!!.findPlayer(4))
                    assertEquals("Chris",fullList!!.findPlayer(4)!!.Firstname)
                    assertEquals("Yasen",fullList!!.findPlayer(4)!!.Lastname)
                    assertEquals(6,fullList!!.findPlayer(4)!!.Playernumber)
                    assertEquals(2000.0,fullList!!.findPlayer(4)!!.Playerprice)
                    assertEquals("Center Defensive Midfielder",fullList!!.findPlayer(4)!!.Playerposition)

                    assertTrue(fullList!!.updatePlayer(4,Player("Urama","Shun","Striker",550.00,20,false)))
                    assertEquals("Urama",fullList!!.findPlayer(4)!!.Firstname)
                    assertEquals("Shun",fullList!!.findPlayer(4)!!.Lastname)
                    assertEquals(20,fullList!!.findPlayer(4)!!.Playernumber)
                    assertEquals(550.00,fullList!!.findPlayer(4)!!.Playerprice)
                    assertEquals("Striker",fullList!!.findPlayer(4)!!.Playerposition)
                }
            }


        }


        }
    }

