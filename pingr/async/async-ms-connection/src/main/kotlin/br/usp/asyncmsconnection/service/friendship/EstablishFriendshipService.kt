package br.usp.asyncmsconnection.service.friendship

import br.usp.asyncmsconnection.model.entity.Friendship

interface EstablishFriendshipService {

    fun createAndPublishFriendship(friendship: Friendship)
}
