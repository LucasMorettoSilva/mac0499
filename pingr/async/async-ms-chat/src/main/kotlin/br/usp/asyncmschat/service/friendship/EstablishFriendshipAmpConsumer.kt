package br.usp.asyncmschat.service.friendship

import br.usp.asyncmschat.model.entity.Friendship

interface EstablishFriendshipAmpConsumer {

    fun consume(friendship: Friendship)
}
