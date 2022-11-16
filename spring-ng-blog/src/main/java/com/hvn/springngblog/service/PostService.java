package com.hvn.springngblog.service;

import com.hvn.springngblog.dto.PostRequest;
import com.hvn.springngblog.exception.PostNotFoundException;
import com.hvn.springngblog.model.Post;
import com.hvn.springngblog.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthService authService;

    public void createPost(PostRequest postRequest) {
        Post post = mapToEntity(postRequest);
        postRepository.save(post);
    }

    public List<PostRequest> showAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public PostRequest getSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found for this"));
        return mapToDto(post);
    }


    private PostRequest mapToDto(Post post) {
        PostRequest postRequest = PostRequest.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .username(post.getUsername())
                .build();
        return postRequest;
    }

    private Post mapToEntity(PostRequest postRequest) {
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User not Found"));
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .createdDate(Instant.now())
                .username(loggedInUser.getUsername())
                .updateDate(Instant.now())
                .build();
        return post;
    }
}
