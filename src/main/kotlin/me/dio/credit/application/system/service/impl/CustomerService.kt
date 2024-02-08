package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.entity.Custumer
import me.dio.credit.application.system.repository.CustumerRepository
import me.dio.credit.application.system.service.ICustumerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val custumerRepository: CustumerRepository
): ICustumerService {
    override fun save(custumer: Custumer): Custumer = this.custumerRepository.save(custumer)


    override fun findById(id: Long): Custumer = this.custumerRepository.findById(id).orElseThrow {
        throw  RuntimeException("Id $id not found")
    }


    override fun delete(id: Long) = this.custumerRepository.deleteById(id)

}