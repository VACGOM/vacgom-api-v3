package kr.co.vacgom.domain.member;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
}
