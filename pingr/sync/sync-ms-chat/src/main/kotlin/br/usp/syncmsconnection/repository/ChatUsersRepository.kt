package br.usp.syncmsconnection.repository

import br.usp.syncmsconnection.model.entity.ChatUsers
import br.usp.syncmsconnection.model.entity.ChatUsersId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ChatUsersRepository: JpaRepository<ChatUsers, ChatUsersId> {

    @Query(
        nativeQuery = true,
        value = "select c.user_email from chats_users c where c.chat_id = :chatId"
    )
    fun findAllUsersFromChat(
        @Param("chatId") chatId: String): List<String>

    @Query(
        nativeQuery = true,
        value =
            "select c1.chat_id from chats_users c1 " +
                "join chats_users c2 on c1.chat_id = c2.chat_id " +
            "where c1.user_email = :userEmail1 " +
            "and c2.user_email = :userEmail2"
    )
    fun findCommonChatId(
        @Param("userEmail1") userEmail1: String,
        @Param("userEmail2") userEmail2: String): String?
}
