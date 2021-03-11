package com.test.tictactoe.controller

import com.test.tictactoe.model.GameValidation
import com.test.tictactoe.service.CheckResultService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(GameController.PATH)
class GameController @Autowired constructor(private val checkResultService: CheckResultService) {

    companion object {
        const val PATH = "/jogovelha"
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Check if TicTacToe game has a winner")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "You have a winner!", content = [
            Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "You have a draw!", content = [
            Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "503", description = "Invalid data input!", content = [
            Content(mediaType = MediaType.APPLICATION_JSON_VALUE)])]
    )
    fun check(@Valid @RequestBody gameValidation : GameValidation) : ResponseEntity<String> {
        val gameArray = checkResultService.mapGameToIntArray(gameValidation)

        return if(checkResultService.isVelha(gameArray)){
            ResponseEntity.ok("You have a winner!")
        } else {
            ResponseEntity<String>("You have a draw!", HttpStatus.NOT_FOUND)
        }
    }
}