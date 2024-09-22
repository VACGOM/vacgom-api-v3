package kr.co.vacgom.core.member.application

import kr.co.vacgom.core.member.infra.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun istValidMember(memberId: Long): Boolean {
        return memberRepository.existsById(memberId)
    }
}
