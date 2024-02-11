package me.dio.credit.application.system.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal

class CustomerUpdateDto(
    @field:NotEmpty(message = "Nome não pode ser nulo")
    val firstName: String,

    @field:NotEmpty(message = "Sobrenome não pode ser nulo")
    val lastName: String,

    @field:NotNull(message = "Salário não pode ser nulo")
    val income: BigDecimal,

    @field:NotEmpty(message = "CEP não pode ser nulo")
    val zipCode: String,

    @field:NotEmpty(message = "Rua não pode ser nulo")
    val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
