package com.test.tictactoe.controller

import com.test.tictactoe.model.GameValidation
import com.test.tictactoe.service.ICheckResultService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping(GameController.PATH)
class GameController @Autowired constructor(private val checkResultService: ICheckResultService) {

    companion object {
        const val PATH = "/jogovelha"
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Check if TicTacToe game has a winner")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "You have a winner!", content = [
            Content(mediaType = MediaType.TEXT_PLAIN_VALUE)]),
        ApiResponse(responseCode = "404", description = "You have a draw!", content = [
            Content(mediaType = MediaType.TEXT_PLAIN_VALUE)]),
        ApiResponse(responseCode = "400", description = "Invalid data input!", content = [
            Content(mediaType = MediaType.TEXT_PLAIN_VALUE)])]
    )

    @ResponseBody
    fun check(@Valid @RequestBody gameValidation : GameValidation, response : HttpServletResponse) : String {
        val gameArray = checkResultService.mapGameToIntArray(gameValidation)

        return if(checkResultService.isVelha(gameArray)){
            response.status = HttpStatus.OK.value()
            "You have a winner!"
        } else {
            response.status = HttpStatus.NOT_FOUND.value()
            "You have a draw!"
        }
    }
}