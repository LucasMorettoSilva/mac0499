package br.usp.syncmsconnection.service.user

import br.usp.syncmsconnection.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository): UserService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun existsByEmail(email: String): Boolean {
        log.info("existsByEmail() : validating user existence : {}", email)

        val exists = userRepository.existsById(email)

        log.info("existsByEmail() : user exists : {}", exists)

        return exists
    }
}
