package br.usp.syncmsconnection.service.chat

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.entity.ChatUsers
import br.usp.syncmsconnection.repository.ChatUsersRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChatUsersServiceImpl(
    private val chatUsersRepository: ChatUsersRepository
): ChatUsersService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun save(chatUsers: ChatUsers): ChatUsers {
        log.info("save() : inserting new relation to chat_users : {}", chatUsers)

        val entity = chatUsersRepository.save(chatUsers)

        log.info("save() : relation successfully inserted")

        return entity
    }


    override fun findCommonChatId(userEmail1: String, userEmail2: String): String? {
        log.info(
            "findCommonChatId() : searching for common chat among users : [{}, {}]",
            userEmail1,
            userEmail2
        )

        val chatId = chatUsersRepository.findCommonChatId(userEmail1, userEmail2)

        log.info("findCommonChatId() : common chat id found : {}", chatId)

        return chatId
    }

    override fun addUserToChat(userEmail: String, chat: Chat): ChatUsers {
        log.info(
            "addUserToChat() : userEmail : {} : chat : {}",
            userEmail,
            chat
        )

        val chatUsers = chatUsersRepository.save(ChatUsers(chat.id, userEmail))

        log.info(
            "addUserToChat() : user successfully inserted to chat"
        )

        return chatUsers
    }

    override fun findAllUsersFromChat(chatId: String): List<String> {
        log.info("findAllUsersFromChat() : chatId : {}", chatId)

        val users = chatUsersRepository.findAllUsersFromChat(chatId)

        log.info("findAllUsersFromChat() : total users found : {}", users.size)

        return users
    }
}
