package br.usp.asyncmsconnection.service.friendship

import br.usp.asyncmsconnection.model.entity.Friendship

interface FriendshipService {

    fun save(friendship: Friendship): Friendship

    fun exists(friendship: Friendship): Boolean

    fun findAll(): List<Friendship>

    fun find(userEmail1: String, userEmail2: String): Friendship?
}
