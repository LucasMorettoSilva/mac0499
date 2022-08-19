package br.usp.syncmsconnection.controller

import br.usp.syncmsconnection.model.response.FullChat
import br.usp.syncmsconnection.service.chat.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chats")
class ChatController(
    private val chatService: ChatService
) {

    @GetMapping
    fun findAll() = chatService.findAll()

    @GetMapping("{email1}/user/{email2}")
    fun find(
        @PathVariable email1: String,
        @PathVariable email2: String): ResponseEntity<FullChat> =
        ResponseEntity.ok(chatService.findFullChatBetweenUsers(email1, email2))
}
