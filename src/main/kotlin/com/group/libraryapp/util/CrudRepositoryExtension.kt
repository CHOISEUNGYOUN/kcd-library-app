package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository

fun fail(): Nothing {
  throw IllegalArgumentException()
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T =
  findById(id).orElseThrow { IllegalArgumentException("${id}를 이용해 데이터를 찾을 수 없습니다!") }