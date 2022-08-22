package br.usp.syncmsconnection.service.message

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.entity.Message
import br.usp.syncmsconnection.model.request.MessageRequest
import br.usp.syncmsconnection.service.chat.ChatService
import br.usp.syncmsconnection.service.chat.ChatUsersService
import br.usp.syncmsconnection.service.friendship.FriendshipService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SendMessageServiceImpl(
    private val friendshipService: FriendshipService,
    private val chatService: ChatService,
    private val chatUsersService: ChatUsersService,
    private val messageService: MessageService
) : SendMessageService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun sendMessage(messageReq: MessageRequest): Message {
        log.info("sendMessage() : messageRequest : {}", messageReq)

        validateFriendshipExistence(messageReq)

        val commonChat = createOrGetCommonChat(messageReq)

        val msg = Message()
        msg.chatId = commonChat?.id
        msg.sender = messageReq.senderEmail
        msg.content = messageReq.message

        return messageService.save(msg)
    }

    private fun validateFriendshipExistence(messageRequest: MessageRequest) {
        log.info("validateFriendshipExistence() : messageRequest : {}", messageRequest)

        val usersAreFriends = friendshipService.exists(
            messageRequest.senderEmail,
            messageRequest.recipientEmail
        )

        if (!usersAreFriends) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "users are not friends"
            )
        }
    }

    private fun createOrGetCommonChat(messageReq: MessageRequest): Chat? {
        val commonChatId = chatUsersService.findCommonChatId(
            messageReq.senderEmail,
            messageReq.recipientEmail
        )

        if (commonChatId == null) {
            log.info("createOrGetCommonChat() : chat between users does not exists")

            val commonChat = chatService.createNewChat()

            chatUsersService.addUserToChat(
                messageReq.senderEmail,
                commonChat
            )

            chatUsersService.addUserToChat(
                messageReq.recipientEmail,
                commonChat
            )

            return commonChat
        }
        return chatService.findById(commonChatId)
    }
}
