package br.usp.syncmsconnection.repository

import br.usp.syncmsconnection.model.entity.ChatUsers
import br.usp.syncmsconnection.model.entity.ChatUsersId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatUsersRepository: JpaRepository<ChatUsers, ChatUsersId>
