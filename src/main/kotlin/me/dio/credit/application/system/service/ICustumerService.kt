package me.dio.credit.application.system.service

import me.dio.credit.application.system.entity.Custumer

interface ICustumerService {
    fun save(custumer: Custumer): Custumer
    fun findById(id: Long): Custumer
    fun delete(id: Long): Custumer
}