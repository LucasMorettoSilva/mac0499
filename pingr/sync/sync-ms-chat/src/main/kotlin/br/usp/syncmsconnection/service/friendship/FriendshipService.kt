package br.usp.syncmsconnection.service.friendship

interface FriendshipService {

    fun exists(userEmail1: String, userEmail2: String): Boolean
}
