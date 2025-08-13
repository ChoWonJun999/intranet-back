package com.odpia.intranet.common.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedBy
	@Column(nullable = false, updatable = false)
	@Comment("생성자")
	private Long createdBy;

	@LastModifiedBy
	@Column()
	@Comment("수정자")
	private Long updatedBy;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	@Comment("생성 일시")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	@Comment("수정 일시")
	private LocalDateTime updatedAt;
}
