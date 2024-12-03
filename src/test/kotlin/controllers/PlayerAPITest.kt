package controllers

import org.example.controllers.PlayerAPI
import org.example.models.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
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
        playerTwo = Player("Zach", "Power", "Left Back", 150.00, 4, false)
        playerThree = Player("Bold", "John", "Striker", 200.00, 9, false)
        playerFour = Player("Anuj", "Yaduv", "Right Wing", 1000.00, 11, false)
        playerFive = Player("Chris", "Yasen", "Center Defensive Midfielder", 2000.00, 6, false)

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
        }
    }
}