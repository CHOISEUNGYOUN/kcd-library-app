package com.group.libraryapp.domain.user

import java.time.LocalDateTime
import javax.persistence.*

/**
 * 상속 관계를 매핑하는 어노테이션
 *
 * create table user
 *
 * 연관관계는 '주인'이 필요하다. (= FK를 누가 들고 있을지 지정해주어야 하기 때문)
 * mappedBy = "필드이름" 을 통해서 주인을 설정하는데, mappedBy를 안쓴 Entity가 주인입니다.
 * 주인으로부터 setter가 불리고 저장이 되어야만 DB에서 연결이 된다!
 *
 * -> 생각해보면.. 테이블 입장에서는 FK를 설정해야 연결이 된거죠... FK는 주인이 들고있죠..
 * 그러니까 주인입장에서 setter를 해야 DB에 반영이 됩니다.
 */
@Entity
class User constructor(
  var name: String,

  val age: Int?,

  /**
   * User 입장에서는 History가 1 : N
   */
  @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
  val histories: MutableList<UserLoanHistory> = mutableListOf(),

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
) {

  init {
    if (name.isBlank()) {
      throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
    }
  }

  /**
   * mappedBy : 연관관계의 주인을 설정하는 옵션
   * 연관관계의 주인이 설정되어야만 연결을 인지하게 된다!!!
   */
//  @OneToOne(mappedBy = "user")
//  var profile: UserProfile? = null

  fun addHistory(createdDateTime: LocalDateTime, bookName: String) {
    this.histories.add(UserLoanHistory(
      user = this,
      createdDateTime = createdDateTime,
      bookName = bookName
    ))
  }

  fun updateName(name: String) {
    this.name = name
  }

}
