package com.example.demoblog.service;

import com.example.demoblog.model.PostModel;
import com.example.demoblog.model.User;
import com.example.demoblog.repository.PostRepository;
import com.example.demoblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostModel> findAll(String username){
        System.out.println("GET GELDİ ");
        Optional<User> user = userRepository.findByUsername(username);
        Long userid = user.get().getId();
        return postRepository.findByUserId(userid);
    }

    public ResponseEntity<PostModel> savePost(String username, PostModel postModel){
     /*   System.out.println("POST GELDİ"+postModel.getTags());
        String username = postModel.getUsers().stream()
                .map(User::getUsername)
                .collect(Collectors.joining());
        System.out.println(username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User NOT FOUND ! : " + username));
        Set<User> setUser = new HashSet<>();
        setUser.add(user);
        postModel.setUsers(setUser);
        Set<PostModel> setPost = new HashSet<>();
        setPost.add(postModel);
        user.setPostModels(setPost);
        userRepository.save(user);*/
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()){
            ResponseEntity.badRequest().build();
        }else
        postModel.setUser(user.get());
        return ResponseEntity.ok(postRepository.save(postModel));
    }

    public ResponseEntity<PostModel> updatePost(Integer id, PostModel bookModel){
        if (!postRepository.findById(id).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(postRepository.save(bookModel));
    }


    public ResponseEntity deleteById(Integer id){
    if (!postRepository.findById(id).isPresent()){
        return ResponseEntity.badRequest().build();
    }
    postRepository.deleteById(id);
    return ResponseEntity.ok().build();
    }

    public ResponseEntity<PostModel> findById(Integer id){
        System.out.println("GET VE ID : "+ id);
        Optional<PostModel> postModel = postRepository.findById(id);
        if (!postModel.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(postModel.get());
    }
}
