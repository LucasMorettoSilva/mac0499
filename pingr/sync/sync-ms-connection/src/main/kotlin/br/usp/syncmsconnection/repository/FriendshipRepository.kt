package br.usp.syncmsconnection.repository

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.model.entity.FriendshipId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendshipRepository : JpaRepository<Friendship, FriendshipId>
