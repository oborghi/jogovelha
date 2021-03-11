package com.test.tictactoe.service

import com.test.tictactoe.model.GameValidation
import org.springframework.stereotype.Service
import java.util.*

@Service
class CheckResultService {

    fun mapGameToIntArray(gameValidation : GameValidation) : Array<Int?> {
        val board = Array<Int?>(9) {null}
        var j = 0

        if(gameValidation.jogo.size != 3){
            throw IllegalArgumentException("Invalid game, a valid game must have three lines!")
        }

        gameValidation.jogo.forEach { item ->
            if (item.length != 3) {
                throw IllegalArgumentException("Invalid game line, a game line must have three chars, ex: 'XOX','OXX','XO ',' XX'... etc!")
            }

            item.forEach {
                if (it.toUpperCase() != 'X' && it.toUpperCase() != 'O' && it.toUpperCase() != ' ') {
                    throw IllegalArgumentException("Invalid game character the game character must be 'X' or 'O' or ' '!")
                } else if (it.toUpperCase() == 'X') {
                    board[j] = 1
                } else if (it.toUpperCase() == 'O') {
                    board[j] = 0
                }
                j += 1
            }
        }

        return board
    }

    fun isVelha(jogo : Array<Int?>) : Boolean {

        if (jogo.size != 9) {
            throw IllegalArgumentException("A game must be a array with 9 positions.")
        }

        val victoryPatterns: Array<IntArray> = arrayOf(
                intArrayOf(0, 1, 2),
                intArrayOf(0, 4, 8),
                intArrayOf(0, 3, 6),
                intArrayOf(1, 4, 7),
                intArrayOf(2, 5, 8),
                intArrayOf(2, 4, 6),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
        )

        victoryPatterns.forEach {
            if (Objects.nonNull(jogo[it[0]]) && jogo[it[0]] == jogo[it[1]] && jogo[it[0]] == jogo[it[2]]){
                return true
            }
        }

        return false
    }
}