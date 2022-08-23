package br.usp.syncmsconnection.controller

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.service.friendship.EstablishFriendshipService
import br.usp.syncmsconnection.service.friendship.FriendshipService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/friendship")
class FriendshipController(
    private val friendshipService: FriendshipService,
    private val establishFriendshipService: EstablishFriendshipService
) {

    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "gets all friendships from database",
        description = "returns all friendships from database as a JSON Array"
    )
    fun findAll() = friendshipService.findAll()

    @GetMapping(
        value = ["{email1}/user/{email2}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "gets friendship from 2 given users",
        description = "returns friendship info from 2 given users"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Success response. Friendship exists."
        ),
        ApiResponse(
            responseCode = "404",
            description = "Friendship does not exist. Not Found"
        ),
    ])
    fun find(
        @Parameter(
            description = "email of first user",
            example = "user1@email.com"
        )
        @PathVariable email1: String,

        @Parameter(
            description = "email of second user",
            example = "user2@email.com"
        )
        @PathVariable email2: String) =
        friendshipService.find(email1, email2)

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "establishes a friendship for 2 given users",
        description = "establishes a friendship for 2 given users"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "201",
            description = "Success response. Friendship established."
        ),
        ApiResponse(
            responseCode = "404",
            description = "One of the given users do no exist."
        ),
        ApiResponse(
            responseCode = "409",
            description = "Friendship already exists."
        ),
    ])
    fun createFriendship(
        @Valid @RequestBody friendship: Friendship) =
        establishFriendshipService.createFriendship(friendship)
}

