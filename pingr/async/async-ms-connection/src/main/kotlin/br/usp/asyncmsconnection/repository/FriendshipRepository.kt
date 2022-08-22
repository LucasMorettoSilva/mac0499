package br.usp.asyncmsconnection.repository

import br.usp.asyncmsconnection.model.entity.Friendship
import br.usp.asyncmsconnection.model.entity.FriendshipId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendshipRepository : JpaRepository<Friendship, FriendshipId>
