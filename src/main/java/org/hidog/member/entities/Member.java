package org.hidog.member.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hidog.file.entities.FileInfo;
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

    @Column(length = 10, nullable = false)
    private Long zipcode;

    @Column(length = 60, nullable = false)
    private String address;

    @Column(length = 60)
    private String detailAddress;

    @ToString.Exclude
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Authorities> authorities;

    @Transient
    private FileInfo profileImage;

}