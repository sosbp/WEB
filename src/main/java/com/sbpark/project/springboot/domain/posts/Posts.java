package com.sbpark.project.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter //필드의 Getter 메소드 생성
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스 - 카멜케이스 이름 -> 언더스코어 네이밍(_)으로 테이블 이름 매칭
//실제 DB의 테이블과 매칭될 Entity 클래스 + 엔터티 클래스에서는 절대 Setter 메소드 만들지 않음
public class Posts {

    @Id //해당 테이블의 PK 필드
    @GeneratedValue //PK의 생성 규칙
    private long id;

    @Column(length = 500, nullable = false) //기본값 외에 추가로 변경이 필요한 옵션이 있을 경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;


    @Builder //해당 클래스의 빌더 패턴 클래슷 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
