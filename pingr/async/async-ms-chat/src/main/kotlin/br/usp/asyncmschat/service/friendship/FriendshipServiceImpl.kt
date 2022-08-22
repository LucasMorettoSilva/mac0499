package br.usp.asyncmschat.service.friendship

import br.usp.asyncmschat.model.entity.Friendship
import br.usp.asyncmschat.model.entity.FriendshipId
import br.usp.asyncmschat.repository.FriendshipRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FriendshipServiceImpl(
    private val friendshipRepo: FriendshipRepository
): FriendshipService {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun save(friendship: Friendship): Friendship {
        log.info("save() : updating friendship : {}", friendship)

        val entity = friendshipRepo.save(friendship)

        log.info("save() : friendship successfully updated : {}", entity)

        return entity
    }

    override fun exists(userEmail1: String, userEmail2: String): Boolean =
        exists(Friendship(userEmail1, userEmail2))

    override fun exists(friendship: Friendship): Boolean {
        log.info("exists() : checking existence of friendship : {}", friendship)

        val friendshipExists = friendshipRepo.existsById(
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

        log.info("exists() : friendship exists : {}", friendshipExists)

        return friendshipExists
    }

    override fun find(userEmail1: String, userEmail2: String): Friendship? {
        val friendshipId = FriendshipId(userEmail1, userEmail2)

        log.info("find() : searching for friendship : {}", friendshipId)

        var friendship = friendshipRepo.findByIdOrNull(friendshipId)

        if (friendship != null) {
            log.info("find() : friendship successfully found")

            return friendship
        }

        friendship = friendshipRepo.findByIdOrNull(
            FriendshipId(userEmail2, userEmail1)
        )

        if (friendship == null) {
            log.info("find() : friendship not found")

            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "friendship between users not found"
            )
        }

        log.info("find() : friendship successfully found")

        return friendship
    }
}
