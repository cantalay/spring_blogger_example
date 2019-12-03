package com.example.demoblog.controller;

import com.example.demoblog.model.PostModel;
import com.example.demoblog.model.User;
import com.example.demoblog.repository.UserRepository;
import com.example.demoblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{username}")
    public List<PostModel> findAll(@PathVariable String username){

        return postService.findAll(username);
    }

    @GetMapping(value = "/getone/{id}")
    public ResponseEntity findOne(@PathVariable  Integer id){
        return postService.findById(id);
    }

    @PostMapping("/{username}")
    public ResponseEntity savePost(@PathVariable String username, @Valid @RequestBody PostModel postModel){
        return postService.savePost(username, postModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostModel> update(@PathVariable Integer id, @Valid @RequestBody PostModel bookModel){
        return postService.updatePost(id,bookModel);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity deleteById(@PathVariable Integer id){
        return postService.deleteById(id);
    }

}
