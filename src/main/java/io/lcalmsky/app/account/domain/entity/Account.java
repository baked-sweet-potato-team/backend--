package io.lcalmsky.app.account.domain.entity;

import io.lcalmsky.app.domain.entity.AuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @Getter @ToString
public class Account extends AuditingEntity {
    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String name;

    //가입 시 설문조사 (나이)
    private String accountAge;

    //성별
    private String accountGender;

    //평소 스타일
    private String accountStyle;

    //퍼스널 컬러
    private String accountColor;

    private boolean isValid;

    private String emailToken;

    private LocalDateTime joinedAt;

    private LocalDateTime emailTokenGeneratedAt;

    /*
    @Embedded
    private Profile profile;
    /*
    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder @Getter @ToString
    public static class Profile {
        private String age;
        private String gender;
        private String style;
        private String color;
        @Lob @Basic(fetch = FetchType.EAGER)
        private String image;
    }

     */

    public void generateToken() {
        this.emailToken = UUID.randomUUID().toString();
        this.emailTokenGeneratedAt = LocalDateTime.now();
    }

    public void verified() {
        this.isValid = true;
        joinedAt = LocalDateTime.now();
    }

    public boolean enabledToSendEmail() {
        return this.emailTokenGeneratedAt.isBefore(LocalDateTime.now().minusMinutes(5));
    }

}
