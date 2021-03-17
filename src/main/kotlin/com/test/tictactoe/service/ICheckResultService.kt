package com.test.tictactoe.service

import com.test.tictactoe.model.GameValidation

interface ICheckResultService {
    fun mapGameToIntArray(gameValidation : GameValidation) : Array<Int?>
    fun isVelha(jogo : Array<Int?>) : Boolean
}