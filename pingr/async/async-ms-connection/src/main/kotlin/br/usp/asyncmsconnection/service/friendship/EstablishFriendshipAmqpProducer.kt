package br.usp.asyncmsconnection.service.friendship

import br.usp.asyncmsconnection.model.entity.Friendship

interface EstablishFriendshipAmqpProducer {

    fun publish(friendship: Friendship)
}
