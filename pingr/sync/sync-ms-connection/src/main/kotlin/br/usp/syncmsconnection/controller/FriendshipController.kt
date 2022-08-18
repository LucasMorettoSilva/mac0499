package br.usp.syncmsconnection.controller

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.service.friendship.EstablishFriendshipService
import br.usp.syncmsconnection.service.friendship.FriendshipService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/friendship")
class FriendshipController(
    private val friendshipService: FriendshipService,
    private val establishFriendshipService: EstablishFriendshipService) {

    @GetMapping
    fun findAll() = friendshipService.findAll()

    @PostMapping
    fun createFriendship(@Valid @RequestBody friendship: Friendship) =
        establishFriendshipService.createFriendship(friendship)
}
