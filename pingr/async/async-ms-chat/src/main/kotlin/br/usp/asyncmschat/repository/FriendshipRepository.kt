package br.usp.asyncmschat.repository

import br.usp.asyncmschat.model.entity.Friendship
import br.usp.asyncmschat.model.entity.FriendshipId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendshipRepository : JpaRepository<Friendship, FriendshipId>
