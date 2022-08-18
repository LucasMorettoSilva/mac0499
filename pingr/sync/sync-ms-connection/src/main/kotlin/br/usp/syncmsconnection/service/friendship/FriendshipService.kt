package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship

interface FriendshipService {

    fun save(friendship: Friendship): Friendship

    fun exists(friendship: Friendship): Boolean

    fun findAll(): List<Friendship>

    fun find(userEmail1: String, userEmail2: String): Friendship?
}
