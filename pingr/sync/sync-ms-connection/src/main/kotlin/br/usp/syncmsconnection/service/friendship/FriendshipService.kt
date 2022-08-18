package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship
import org.springframework.http.ResponseEntity

interface FriendshipService {

    fun save(friendship: Friendship): ResponseEntity<Friendship>

    fun exists(friendship: Friendship): Boolean

    fun findAll(): List<Friendship>
}
