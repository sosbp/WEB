package com.sbpark.project.springboot.config.auth;

import com.sbpark.project.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션 disable 설정
                .and()
                    .authorizeRequests() //URL별 권한 관리 설정 옵션의 시작점, authorizeRequests가 선언되어야 antMatchers 옵션 사용 가능
                    //antMatchers -> 권한 관리 대상 지정 옵션, URL/HTTP 메소드별로 관리 가능,
                    // "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한 부여,
                    // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 접근 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    //anyRequest -> 설정된 값들 이외 나머지 URL들을 나타냄
                    // authenticated()을 추가하여 나머지 URL들을 모두 인증된 사용자(=로그인한 사용자)들에게만 허용하게 함
                    .anyRequest().authenticated()
                .and()
                    .logout().logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공 시 "/" 주소로 이동
                .and()
                    .oauth2Login() //OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customOAuth2UserService); //소셜로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                                                                   //리소스 서버(=소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }

}
