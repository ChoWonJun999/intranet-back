package com.odpia.intranet.user.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;

import com.odpia.intranet.common.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "TB_SYS_USER")
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("PK")
	private Long id;

	@Column(nullable = false, length = 50, unique = true)
	@Comment("로그인 ID")
	private String loginId;

	@Column(nullable = false, length = 50)
	@Comment("비밀번호")
	private String password;

	@Column(length = 20)
	@Comment("핸드폰 번호")
	private String phoneNumber;

	@Column(nullable = false)
	@Comment("로그인 실패 횟수")
	private Integer loginFailCount = 0;

	@Comment("최종 로그인 일시")
	private LocalDateTime lastLoginAt;

	@Column(length = 45)
	@Comment("최종 로그인 IP")
	private String lastLoginIp;

	@Column(nullable = false, length = 100, unique = true)
	@Comment("이메일")
	private String email;

	@Column(name = "NAME", nullable = false, length = 50)
	@Comment("이름")
	private String username;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	@Comment("상태")
	private UserStatus status; // ACTIVE, INACTIVE

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	@Comment("권한")
	private RoleType role; // ROLE_USER, ROLE_ADMIN
}
