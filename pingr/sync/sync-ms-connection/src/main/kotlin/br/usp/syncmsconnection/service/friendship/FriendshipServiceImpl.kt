package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.model.entity.FriendshipId
import br.usp.syncmsconnection.repository.FriendshipRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FriendshipServiceImpl(
    private val friendshipRepo: FriendshipRepository): FriendshipService {

    override fun findAll(): List<Friendship> = friendshipRepo.findAll()

    override fun save(friendship: Friendship): ResponseEntity<Friendship> =
        ResponseEntity.ok(friendshipRepo.save(friendship))

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
}
