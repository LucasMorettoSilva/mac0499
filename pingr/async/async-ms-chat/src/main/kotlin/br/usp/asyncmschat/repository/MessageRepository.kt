package br.usp.asyncmschat.repository

import br.usp.asyncmschat.model.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, String> {

    fun findByChatIdOrderByCreationDateDesc(chatId: String): List<Message>
}
