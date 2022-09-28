package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

  fun existsByBookNameAndStatus(bookName: String, status: UserLoanStatus): Boolean

}