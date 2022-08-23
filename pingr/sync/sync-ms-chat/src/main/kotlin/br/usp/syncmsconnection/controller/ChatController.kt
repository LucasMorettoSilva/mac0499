package br.usp.syncmsconnection.controller

import br.usp.syncmsconnection.model.request.MessageRequest
import br.usp.syncmsconnection.service.chat.ChatService
import br.usp.syncmsconnection.service.message.SendMessageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/chats")
class ChatController(
    private val chatService: ChatService,
    private val messageService: SendMessageService
) {

    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "gets all chats from database",
        description = "returns all chats from database as a JSON Array"
    )
    fun findAll() = chatService.findAll()

    @GetMapping(
        value = ["{chatId}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "gets chat with given id",
        description = "returns chat with given id"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Success response. Chat exists."
        ),
        ApiResponse(
            responseCode = "404",
            description = "Chat with given id does not exist. Not Found"
        ),
    ])
    fun findById(
        @Parameter(
            description = "chat id in UUID v4 format",
            example = "54b2a47a-7153-4925-8011-b23985cbcd4d"
        )
        @PathVariable chatId: String) =
        chatService.findFullChatById(chatId)


    @GetMapping(
        value = ["user/{email1}/user/{email2}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "gets chat between 2 given users",
        description = "returns chat info between 2 given users"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Success response. Returns all chat data."
        ),
        ApiResponse(
            responseCode = "404",
            description = "Chat does not exist. Not Found."
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
        chatService.findFullChatBetweenUsers(email1, email2)

    @PostMapping(
        value = ["messages"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "sends a message for 2 given users",
        description = "sends a message for 2 given users"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "201",
            description = "Success response. Message sent."
        ),
        ApiResponse(
            responseCode = "400",
            description = "Users are not friends."
        ),
        ApiResponse(
            responseCode = "404",
            description = "One of the given users do no exist."
        )
    ])
    fun sendMessage(
        @Valid @RequestBody messageRequest: MessageRequest) =
        messageService.sendMessage(messageRequest)
}
