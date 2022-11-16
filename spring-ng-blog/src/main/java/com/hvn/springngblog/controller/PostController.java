package com.hvn.springngblog.controller;

import com.hvn.springngblog.dto.PostRequest;
import com.hvn.springngblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostRequest>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostRequest> getSignlePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.getSinglePost(id), HttpStatus.OK);
    }
}
