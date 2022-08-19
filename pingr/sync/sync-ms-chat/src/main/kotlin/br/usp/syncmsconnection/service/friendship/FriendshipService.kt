package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship

interface FriendshipService {

    fun exists(userEmail1: String, userEmail2: String): Boolean
}
