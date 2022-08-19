package br.usp.syncmsconnection.repository

import br.usp.syncmsconnection.model.entity.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository: JpaRepository<Chat, String>
