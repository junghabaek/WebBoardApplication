package com.jungha.ShoppingMall.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
