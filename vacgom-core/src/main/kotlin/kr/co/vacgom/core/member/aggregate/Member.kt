package kr.co.vacgom.core.member.aggregate

import kr.co.vacgom.core.member.constants.GrantedAuthorityRole

data class Member(
    val id: Long? = null,
    val name: String,
    val role: GrantedAuthorityRole
)