package br.usp.asyncmschat.controller

import br.usp.asyncmschat.model.request.MessageRequest
import br.usp.asyncmschat.service.chat.ChatService
import br.usp.asyncmschat.service.message.SendMessageService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/chats")
class ChatController(
    private val chatService: ChatService,
    private val messageService: SendMessageService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll() = chatService.findAll()

    @GetMapping("{chatId}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(
        @PathVariable chatId: String) =
        chatService.findFullChatById(chatId)


    @GetMapping("user/{email1}/user/{email2}")
    @ResponseStatus(HttpStatus.OK)
    fun find(
        @PathVariable email1: String,
        @PathVariable email2: String) =
        chatService.findFullChatBetweenUsers(email1, email2)

    @PostMapping("messages")
    @ResponseStatus(HttpStatus.CREATED)
    fun sendMessage(
        @Valid @RequestBody messageRequest: MessageRequest) =
        messageService.sendMessage(messageRequest)
}
