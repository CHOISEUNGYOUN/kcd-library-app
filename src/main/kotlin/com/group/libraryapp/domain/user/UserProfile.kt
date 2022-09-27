package com.group.libraryapp.domain.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

/**
 * create table user_profile
 */
@Entity
class UserProfile(

  val height: Int,

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
) {
  @OneToOne
  var user: User? = null
}