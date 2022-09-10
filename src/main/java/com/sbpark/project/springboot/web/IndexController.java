package com.sbpark.project.springboot.web;

import com.sbpark.project.springboot.config.auth.LoginUser;
import com.sbpark.project.springboot.config.auth.dto.SessionUser;
import com.sbpark.project.springboot.service.posts.PostsService;
import com.sbpark.project.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
//    private final HttpSession httpSession; // --> @LoginUser로 대체

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        //CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser 저장
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) { //세션에 저장된 값이 있는 경우
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
