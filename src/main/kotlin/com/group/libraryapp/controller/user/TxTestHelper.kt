package com.group.libraryapp.controller.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TxTestHelper {

  @Transactional
  fun tx(func: () -> Unit) {
    func()
  }

}