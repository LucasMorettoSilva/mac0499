package br.usp.syncmsconnection.service.chat

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.response.FullChat
import org.springframework.stereotype.Repository

@Repository
interface ChatService {

    fun createNewChat(): Chat

    fun save(chat: Chat): Chat

    fun findById(id: String): Chat?

    fun findFullChatById(id: String): FullChat

    fun findAll(): List<Chat>

    fun findFullChatBetweenUsers(
        userEmail1: String,
        userEmail2: String): FullChat
}
