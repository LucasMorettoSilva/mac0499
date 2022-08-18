package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EstablishFriendshipServiceImpl(
    private val friendshipService: FriendshipService,
    private val userService: UserService): EstablishFriendshipService {

    override fun createFriendship(friendship: Friendship) {
        validateUserExistence(friendship.userEmail1)
        validateUserExistence(friendship.userEmail2)

        validateFriendshipExistence(friendship)

        friendshipService.save(friendship)
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
