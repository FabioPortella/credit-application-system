package me.dio.credit.application.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.dio.credit.application.system.entity.Address
import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.exception.BusinessException
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should create customer`() {
        //given - dados que precisamos receber
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        //when - metodo que queremos testar
        val actual: Customer = customerService.save(fakeCustomer)

        //then - acertivas - retorno que queremos
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeid: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeid)
        every { customerRepository.findById(fakeid) } returns Optional.of(fakeCustomer)

        //when
        val actual: Customer = customerService.findById(fakeid)

        //then
        Assertions.assertThat(actual).isNotNull                         // verifica se não é nulo
        Assertions.assertThat(actual)
            .isExactlyInstanceOf(Customer::class.java)                  // verifica se retornou uma classe do tipo Customer
        Assertions.assertThat(actual).isSameAs(fakeCustomer)            // verifica se retornou um Customer
        verify(exactly = 1) { customerRepository.findById(fakeid) }     // verifica se retorna somente uma vez
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        //given
        val fakeid: Long = Random().nextLong()
        every { customerRepository.findById(fakeid) } returns Optional.empty()

        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeid) }
            .withMessage("Id $fakeid not found")
        verify(exactly = 1) { customerRepository.findById(fakeid) }

    }

    @Test
    fun `should delete customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs

        //when
        customerService.delete(fakeId)

        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    private fun buildCustomer(
        firstName: String = "Cami",
        lastName: String = "Cavalcante",
        cpf: String = "28475934625",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        email: String = "camila@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua da Cami",
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        income = income,
        email = email,
        password = password,
        address = Address (
            zipCode = zipCode,
            street = street
        ),
        id = id
    )
}