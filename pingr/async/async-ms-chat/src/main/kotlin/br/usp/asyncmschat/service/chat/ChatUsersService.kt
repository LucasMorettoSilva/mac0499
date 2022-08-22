package br.usp.asyncmschat.service.chat

import br.usp.asyncmschat.model.entity.Chat
import br.usp.asyncmschat.model.entity.ChatUsers

interface ChatUsersService {

    fun save(chatUsers: ChatUsers): ChatUsers

    fun findCommonChatId(userEmail1: String, userEmail2: String): String?

    fun addUserToChat(userEmail: String, chat: Chat): ChatUsers

    fun findAllUsersFromChat(chatId: String): List<String>
}
