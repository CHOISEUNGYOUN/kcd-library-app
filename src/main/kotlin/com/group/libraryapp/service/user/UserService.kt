package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
  private val userRepository: UserRepository,
) {

  @Transactional
  fun saveUser(request: UserCreateRequest) {
    val newUser = User(request.name, request.age)
    userRepository.save(newUser)
  }

  @Transactional(readOnly = true)
  fun getUsers(): List<UserResponse> {
    return userRepository.findAll()
      .map { user -> UserResponse.of(user) }
  }

  /**
   * 쿼리가 몇 번 나갈까요?!!! -> 5번
   *
   * 트랜잭션이 붙는 순간 영속성 컨텍스트가 존재하게 되고, 이 덕분에 1차 캐싱이 발동해서 (1차 캐싱 = ID를 기준으로 메모리에 캐싱)
   * 5번 나갈 것 같은게 1번만 나갑니다.
   * 완전히 동일하다는 것은 정말 인스턴스 레퍼런스까지 같다는 겁니다!!!
   */
  @Transactional
  fun getManyUsers() {
    userRepository.findByIdOrThrow(1L)
    userRepository.findByIdOrThrow(1L)
    userRepository.findByIdOrThrow(1L)
    userRepository.findByIdOrThrow(1L)
    userRepository.findByIdOrThrow(1L)
  }

  // Controller updateAndDeleteAndInsert().histories

  /**
   * 쿼리는 어느 시점에 나갈까?!
   */
  @Transactional
  fun updateAndDeleteAndInsert(): User {
    return userRepository.findAll()[0]
  }

  @Transactional
  fun updateUserName(request: UserUpdateRequest) {
    val user = userRepository.findByIdOrThrow(request.id)
    user.updateName(request.name)
  }

  @Transactional
  fun deleteUser(name: String) {
    val user = userRepository.findByName(name) ?: fail()
    userRepository.delete(user)
  }

}