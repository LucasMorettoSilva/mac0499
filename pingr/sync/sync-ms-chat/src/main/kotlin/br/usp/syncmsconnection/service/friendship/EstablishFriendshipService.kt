package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship

interface EstablishFriendshipService {

    fun createFriendship(friendship: Friendship)
}
