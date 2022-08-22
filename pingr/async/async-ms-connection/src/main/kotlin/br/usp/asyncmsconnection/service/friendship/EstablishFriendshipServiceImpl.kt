package br.usp.asyncmsconnection.service.friendship

import br.usp.asyncmsconnection.model.entity.Friendship
import br.usp.asyncmsconnection.service.user.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EstablishFriendshipServiceImpl(
    private val establishFriendshipAmqpProducer: EstablishFriendshipAmqpProducer,
    private val friendshipService: FriendshipService,
    private val userService: UserService): EstablishFriendshipService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun createAndPublishFriendship(friendship: Friendship) {
        log.info("createFriendship() : preparing to create new friendship : {}", friendship)

        validateUserExistence(friendship.userEmail1)
        validateUserExistence(friendship.userEmail2)

        validateFriendshipExistence(friendship)

        friendshipService.save(friendship)

        establishFriendshipAmqpProducer.publish(friendship)
    }

    private fun validateUserExistence(userEmail: String) {
        if (!userService.existsByEmail(userEmail)) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with email $userEmail does not exist"
            )
        }
    }

    private fun validateFriendshipExistence(friendship: Friendship) {
        if (friendshipService.exists(friendship)) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "Friendship already exists"
            )
        }
    }
}
