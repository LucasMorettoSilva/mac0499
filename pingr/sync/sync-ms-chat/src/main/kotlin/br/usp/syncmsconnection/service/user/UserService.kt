package br.usp.syncmsconnection.service.user

interface UserService {

    fun existsByEmail(email: String): Boolean
}
