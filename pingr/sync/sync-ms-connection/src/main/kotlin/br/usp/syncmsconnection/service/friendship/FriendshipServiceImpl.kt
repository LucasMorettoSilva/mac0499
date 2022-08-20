package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.model.entity.FriendshipId
import br.usp.syncmsconnection.repository.FriendshipRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FriendshipServiceImpl(
    private val friendshipRepo: FriendshipRepository): FriendshipService {

    override fun findAll(): List<Friendship> = friendshipRepo.findAll()

    override fun save(friendship: Friendship): Friendship =
        friendshipRepo.save(friendship)

    override fun exists(friendship: Friendship): Boolean {
        return friendshipRepo.existsById(
            FriendshipId(
                friendship.userEmail1,
                friendship.userEmail2
            )
        ) || friendshipRepo.existsById(
            FriendshipId(
                friendship.userEmail2,
                friendship.userEmail1
            )
        )
    }

    override fun find(userEmail1: String, userEmail2: String): Friendship? {
        val friendship = friendshipRepo.findById(
            FriendshipId(userEmail1, userEmail2)
        )

        if (friendship.isPresent) {
            return friendship.get()
        }

        return friendshipRepo.findByIdOrNull(
            FriendshipId(userEmail2, userEmail1)
        ) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "friendship between users not found"
        )
    }
}
