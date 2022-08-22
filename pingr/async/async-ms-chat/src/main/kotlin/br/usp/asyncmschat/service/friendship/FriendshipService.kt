package br.usp.asyncmschat.service.friendship

import br.usp.asyncmschat.model.entity.Friendship

interface FriendshipService {

    fun save(friendship: Friendship): Friendship

    fun exists(friendship: Friendship): Boolean

    fun exists(userEmail1: String, userEmail2: String): Boolean

    fun find(userEmail1: String, userEmail2: String): Friendship?
}
