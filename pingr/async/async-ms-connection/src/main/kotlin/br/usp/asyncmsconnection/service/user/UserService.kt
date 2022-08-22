package br.usp.asyncmsconnection.service.user

interface UserService {

    fun existsByEmail(email: String): Boolean
}
