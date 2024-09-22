package kr.co.vacgom.core.member.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import kr.co.vacgom.core.global.domain.BaseEntity;
import kr.co.vacgom.core.member.domain.constants.GrantedAuthorityRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Nonnull
    @Enumerated(EnumType.STRING)
    private GrantedAuthorityRole role;

    @Nullable
    private Boolean isMaster;
}
