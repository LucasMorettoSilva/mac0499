package br.usp.asyncmsconnection.repository

import br.usp.asyncmsconnection.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String>
