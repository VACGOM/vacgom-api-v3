package core.member.entity;

import core.global.entity.BaseEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import kr.co.vacgom.core.member.constants.GrantedAuthorityRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "TB_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private UUID id;

    @Nonnull
    @Enumerated(EnumType.STRING)
    private GrantedAuthorityRole role;

    @Nonnull
    private String nickname;

}
