package com.example.demoblog.repository;

import com.example.demoblog.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<PostModel,Integer> {

    List<PostModel> findByUserId(Long user_id);


}
