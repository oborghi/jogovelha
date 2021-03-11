package com.test.tictactoe.service

import com.test.tictactoe.model.GameValidation
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CheckResultServiceTest @Autowired constructor(private val checkResultService: CheckResultService) {

    @Test
    fun mapGameToIntArray() {

        var game = GameValidation(jogo = listOf("OXX","XOO","OOX"))
        var gameArray : Array<Int?> = checkResultService.mapGameToIntArray(game)
        var expectedGameArray : Array<Int?> = arrayOf(0,1,1,1,0,0,0,0,1)

        assertArrayEquals(expectedGameArray, gameArray)

        game = GameValidation(jogo = listOf("OX ","XO ","OO "))
        gameArray = checkResultService.mapGameToIntArray(game)
        expectedGameArray = arrayOf(0,1,null,1,0,null,0,0,null)

        assertArrayEquals(expectedGameArray, gameArray)

        var exception = assertThrows(IllegalArgumentException::class.java) {
             game = GameValidation(jogo = listOf("ABB","XOO","OOX"))
            checkResultService.mapGameToIntArray(game)
        }

        assertEquals("Invalid game character the game character must be 'X' or 'O' or ' '!", exception.message)

        exception = assertThrows(IllegalArgumentException::class.java) {
            game = GameValidation(jogo = listOf("XXOO","XOO","OOX"))
            checkResultService.mapGameToIntArray(game)
        }

        assertEquals("Invalid game line, a game line must have three chars, ex: 'XOX','OXX','XO ',' XX'... etc!", exception.message)

        exception = assertThrows(IllegalArgumentException::class.java) {
            game = GameValidation(jogo = listOf("XXO","XOO"))
            checkResultService.mapGameToIntArray(game)
        }

        assertEquals("Invalid game, a valid game must have three lines!", exception.message)
    }

    @Test
    fun isVelha() {
        /* Tic-tac-toe position map

        0 | 1 | 2
        ---------
        3 | 4 | 5
        ---------
        6 | 7 | 8

        */

        /* Scenario
        "X", "O", null,
        "O", "O", null,
        "X", "X", "X"
        */
        var game : Array<Int?> = arrayOf(1,0,null,0,0,null,1,1,1)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "X", "X", "X",
        "O", "O", null,
        "X", "O", null
        */
        game = arrayOf(1,1,1,0,0,null,1,0,null)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "O", "O", null,
        "X", "X", "X",
        "X", "O", null
        */
        game = arrayOf(0,0,null,1,1,1,1,0,null)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "O", "X", null,
        "O", "X", "O",
        "O", null, "X"
        */
        game = arrayOf(0,1,null,0,1,0,0,null,1)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "X", "O", "X",
        null, "O", "X",
        "O", null, "X"
        */
        game = arrayOf(1,0,1,null,0,1,0,null,1)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "X", "O", "X",
        null, "O", "X",
        "O", "O", null
        */
        game = arrayOf(1,0,1,null,0,1,0,0,null)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "O", "X", "X",
        null, "X", "O",
        "X", null, "O"
        */
        game = arrayOf(0,1,1,null,1,0,1,null,0)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "X", "O", "O",
        null, "X", "X",
        "O", null, "X"
        */
        game = arrayOf(1,0,0,null,1,1,0,null,1)
        assertTrue(checkResultService.isVelha(game))

        /* Scenario
        "X", "O", "O",
        "O", "X", "X",
        "X", "O", "O"
        */
        game = arrayOf(1,0,0,0,1,1,1,0,0)
        assertFalse(checkResultService.isVelha(game))

        /* Scenario
        "O", "X", "X",
        "X", "O", "O",
        "O", "O", "X"
        */
        game = arrayOf(0,1,1,1,0,0,0,0,1)
        assertFalse(checkResultService.isVelha(game))

        /* Invalid Array */
        val exception = assertThrows(IllegalArgumentException::class.java) {
            game = arrayOf(0,1,1,1,0,0,0)
            checkResultService.isVelha(game)
        }

        assertEquals("A game must be a array with 9 positions.", exception.message)
    }
}