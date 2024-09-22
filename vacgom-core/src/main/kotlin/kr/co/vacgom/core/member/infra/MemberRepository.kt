package kr.co.vacgom.core.member.infra

import kr.co.vacgom.core.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>
