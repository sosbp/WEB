package com.sbpark.project.springboot.web;

import com.sbpark.project.springboot.service.posts.PostsService;
import com.sbpark.project.springboot.web.dto.PostsResponseDto;
import com.sbpark.project.springboot.web.dto.PostsSaveRequestDto;
import com.sbpark.project.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //@Autowired 대신 생성자로 주입 받는 방식
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestsDto) {
        return postsService.update(id, requestsDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
