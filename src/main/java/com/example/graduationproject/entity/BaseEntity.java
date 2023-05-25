package com.example.graduationproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp  // 데이터를 생성했을 때 시간
    @Column(updatable = false)  // 데이터를 업데이트할 때 관여하지 않음
    private LocalDateTime createTime;

    @UpdateTimestamp    // 데이터를 수정할 때 시간
    @Column(insertable = false) // 테이블에 데이터를 생성할 때 관여하지 않음
    private LocalDateTime updateTime;
}
