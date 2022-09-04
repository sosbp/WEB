package com.sbpark.project.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //해당 Entity 클래스를 상속할 경우, 아래 필드들이 column으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) //해당 Entity 클래스에 Auditing 기능을 포함
public class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장될 때 시간이 자동으로 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity의 값을 변경할 때 시간이 자동으로 저장됨
    private LocalDateTime modifiedDate;
}
