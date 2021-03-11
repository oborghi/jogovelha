package com.test.tictactoe.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.test.tictactoe.model.GameValidation
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.hamcrest.Matchers.*
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
internal class GameValidationControllerTest @Autowired constructor(private val mockMvc: MockMvc, private val objectMapper : ObjectMapper) {

    @Test
    fun check() {
        var gameValidation =  GameValidation(jogo = listOf("XO ","OX "," OX"))
        var json = objectMapper.writeValueAsString(gameValidation)

        mockMvc.perform(
            post("/jogovelha")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isOk)
            .andExpect(content().string(containsString("You have a winner!")))

        gameValidation =  GameValidation(jogo = listOf("OXX","XOO","OOX"))
        json = objectMapper.writeValueAsString(gameValidation)

        mockMvc.perform(
            post("/jogovelha")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isNotFound)
            .andExpect(content().string(containsString("You have a draw!")))

        gameValidation =  GameValidation(jogo = listOf("ABB","XOO","OOX"))
        json = objectMapper.writeValueAsString(gameValidation)

        mockMvc.perform(
            post("/jogovelha")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isBadRequest)
            .andExpect(content().string(containsString("Invalid game character the game character must be 'X' or 'O' or ' '!")))

        gameValidation = GameValidation(jogo = listOf("XXOO","XOO","OOX"))
        json = objectMapper.writeValueAsString(gameValidation)

        mockMvc.perform(
            post("/jogovelha")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isBadRequest)
            .andExpect(content().string(containsString("Invalid game line, a game line must have three chars, ex: 'XOX','OXX','XO ',' XX'... etc!")))

        gameValidation = GameValidation(jogo = listOf("XXO","XOO"))
        json = objectMapper.writeValueAsString(gameValidation)

        mockMvc.perform(
            post("/jogovelha")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isBadRequest)
            .andExpect(content().string(containsString("Invalid game, a valid game must have three lines!")))
    }
}