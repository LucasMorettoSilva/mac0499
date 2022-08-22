package br.usp.asyncmschat.repository

import br.usp.asyncmschat.model.entity.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository: JpaRepository<Chat, String>
