package br.usp.syncmsconnection.service.user

import br.usp.syncmsconnection.repository.ChatRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val chatRepository: ChatRepository): UserService {

    override fun existsByEmail(email: String): Boolean =
        chatRepository.existsById(email)
}
