package controllers

import org.example.controllers.PlayerAPI
import org.example.models.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PlayerAPITest {

    private var playerOne : Player? = null
    private var playerTwo: Player? = null
    private var playerThree: Player? = null
    private var playerFour: Player? = null
    private var playerFive: Player? = null
    private var fullList: PlayerAPI? = PlayerAPI()
    private var noPlayers: PlayerAPI? = PlayerAPI()



    @BeforeEach
    fun setup(){
        playerOne =  Player("Ebidimo","Collins","Left Wing",7)
        playerTwo = Player("Zach","Power","Left Back",3)
        playerThree = Player("Bold","John","Striker",9)
        playerFour = Player("Anuj","Yaduv","Righ Wing",11)
        playerFive = Player("Chris","Yasen","Center Defensive Midfielder",6)

        fullList!!.add(playerOne!!)
        fullList!!.add(playerTwo!!)
        fullList!!.add(playerThree!!)
        fullList!!.add(playerFour!!)
        fullList!!.add(playerFive!!)
    }

    @AfterEach
    fun tearDown(){
        playerOne = null
        playerTwo = null
        playerThree = null
        playerFour = null
        playerFive = null
        fullList = null
        noPlayers = null
    }

    @Test
    fun `adding a player to the full list adds to the ArrayList`(){
        val newPlayer = Player("Sean","Purcell","Right Back",5)
        assertEquals(5,fullList!!.numberOfPlayers())
        assertTrue(fullList!!.add(newPlayer))
        assertEquals(6,fullList!!.numberOfPlayers())
        assertEquals(newPlayer,fullList!!.findPlayer(fullList!!.numberOfPlayers()-1))
    }

    @Test
    fun `adding a player to the no Players list adds to the ArrayList`(){
        val newPlayer = Player("Sean","Purcell","Right Back",5)
        assertEquals(0,noPlayers!!.numberOfPlayers())
        assertTrue(noPlayers!!.add(newPlayer))
        assertEquals(1,noPlayers!!.numberOfPlayers())
        assertEquals(newPlayer,noPlayers!!.findPlayer(noPlayers!!.numberOfPlayers()-1))
    }



}