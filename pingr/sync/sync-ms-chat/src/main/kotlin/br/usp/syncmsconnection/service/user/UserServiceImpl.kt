package br.usp.syncmsconnection.service.user

import br.usp.syncmsconnection.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository): UserService {

    override fun existsByEmail(email: String): Boolean =
        userRepository.existsById(email)
}
