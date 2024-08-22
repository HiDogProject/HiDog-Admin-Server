package org.hidog.member.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hidog.global.entities.BaseEntity;
import org.hidog.member.constants.Authority;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Member extends BaseEntity implements Serializable {
    @Id @GeneratedValue
    private Long seq;

    @Column(length = 65, nullable = false, unique = true)
    private String email;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(length = 40, nullable = false, unique = true)
    private String userName;

    @Column(length = 60, nullable = false)
    private String address;

    @Column(length = 60)
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority; // 권한 필드

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Authorities> authorities;

    // 권한 설정 메소드
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    /*@Transient
    private FileInfo profileImage; */
}