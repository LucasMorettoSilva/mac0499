package br.usp.syncmsconnection.service.message

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.entity.Message
import br.usp.syncmsconnection.model.request.MessageRequest
import br.usp.syncmsconnection.service.chat.ChatService
import br.usp.syncmsconnection.service.chat.ChatUsersService
import br.usp.syncmsconnection.service.friendship.FriendshipService
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

    override fun sendMessage(messageReq: MessageRequest) {
        validateFriendshipExistence(messageReq)

        val commonChat = createOrGetCommonChat(messageReq)

        val msg = Message()
        msg.chatId = commonChat?.id
        msg.userEmail = messageReq.senderEmail
        msg.content = messageReq.message

        messageService.save(msg)
    }

    private fun validateFriendshipExistence(messageRequest: MessageRequest) {
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
