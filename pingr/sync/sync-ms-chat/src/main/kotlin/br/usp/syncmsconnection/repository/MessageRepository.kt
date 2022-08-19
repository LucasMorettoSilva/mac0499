package br.usp.syncmsconnection.repository

import br.usp.syncmsconnection.model.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageRepository : JpaRepository<Message, UUID>
