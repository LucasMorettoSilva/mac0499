package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.model.request.MessageRequest
import br.usp.syncmsconnection.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SendMessageServiceImpl(
    private val friendshipService: FriendshipService,
    private val userService: UserService): SendMessageService {

    override fun sendMessage(messageRequest: MessageRequest) {
        validateFriendshipExistence(messageRequest)

        // todo
        "select c1.chat_id from chats_users c1 join chats_users c2 on c1.chat_id = c2.chat_id\n" +
            "where c1.user_email = 'user1'\n" +
            "and c2.user_email = 'user2';"
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
}
