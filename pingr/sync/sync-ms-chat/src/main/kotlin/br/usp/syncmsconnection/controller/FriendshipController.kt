package br.usp.syncmsconnection.controller

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.service.message.SendMessageService
import br.usp.syncmsconnection.service.friendship.FriendshipService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/friendship")
class FriendshipController(
    private val friendshipService: FriendshipService,
    private val sendMessageService: SendMessageService
) {

    @GetMapping
    fun findAll() = friendshipService.findAll()

    @GetMapping("{email1}/user/{email2}")
    fun find(@PathVariable email1: String, @PathVariable email2: String): ResponseEntity<Friendship> {
        val friendship =
            friendshipService.exists(email1, email2) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(friendship)
    }

    @PostMapping
    fun createFriendship(@Valid @RequestBody friendship: Friendship) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(sendMessageService.sendMessage(friendship))
}
