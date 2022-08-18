package br.usp.syncmsconnection.model.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User(

    @Id
    @Email
    @NotBlank
    @Column(name = "email")
    var email: String,

    @Size(max = 255)
    @Column(name = "first_name")
    var firstName: String?,

    @Size(max = 255)
    @Column(name = "last_name")
    var lastName: String?,

    @CreationTimestamp
    @Column(name = "creation_date")
    var creationDate: LocalDateTime
)
