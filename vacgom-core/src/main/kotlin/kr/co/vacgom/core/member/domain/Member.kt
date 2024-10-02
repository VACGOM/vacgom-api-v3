package kr.co.vacgom.core.member.domain

import com.github.f4b6a3.uuid.UuidCreator
import kr.co.vacgom.core.member.constants.GrantedAuthorityRole
import java.util.*

data class Member(
    val id: UUID = UuidCreator.getTimeOrderedEpoch(),
    val role: GrantedAuthorityRole,
    val nickname: String,
)