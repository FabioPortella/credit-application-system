package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Address
import me.dio.credit.application.system.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Nome não pode ser nulo")
    val firstName: String,

    @field:NotEmpty(message = "Sobrenome não pode ser nulo")
    val lastName: String,

    @field:NotEmpty(message = "CPF não pode ser nulo")
    @field:CPF(message = "CPF inválido")
    val cpf: String,

    @field:NotNull(message = "Salário não pode ser nulo")
    val income: BigDecimal,

    @field:NotEmpty(message = "Email não pode ser nulo")
    @field:Email(message = "Email inválido")
    val email: String,

    @field:NotEmpty(message = "Senha não pode ser nulo")
    val password: String,

    @field:NotEmpty(message = "CEP não pode ser nulo")
    val zipCode: String,

    @field:NotEmpty(message = "Rua não pode ser nulo")
    val street: String
) {
    fun toEntity(): Customer = Customer(

        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
