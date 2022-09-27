package com.group.libraryapp.domain.user

import java.time.LocalDateTime
import javax.persistence.*

/**
 * UserLoanHistory : 유저 = N : 1
 * 외래키는 무조건 여지 없이 여기
 */
@Entity
class UserLoanHistory(

  @ManyToOne(fetch = FetchType.LAZY)
  val user: User, // user_id

  val createdDateTime: LocalDateTime,

  val bookName: String,

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
) {
  fun function() {
    this.user.age
  }
}