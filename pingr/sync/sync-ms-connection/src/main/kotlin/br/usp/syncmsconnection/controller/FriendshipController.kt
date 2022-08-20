package br.usp.syncmsconnection.controller

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.service.friendship.EstablishFriendshipService
import br.usp.syncmsconnection.service.friendship.FriendshipService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/friendship")
class FriendshipController(
    private val friendshipService: FriendshipService,
    private val establishFriendshipService: EstablishFriendshipService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll() = friendshipService.findAll()

    @GetMapping("{email1}/user/{email2}")
    @ResponseStatus(HttpStatus.OK)
    fun find(
        @PathVariable email1: String,
        @PathVariable email2: String) =
        friendshipService.find(email1, email2)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createFriendship(
        @Valid @RequestBody friendship: Friendship) =
        establishFriendshipService.createFriendship(friendship)
}
