package com.jungha.ShoppingMall.web;

//import com.jungha.ShoppingMall.config.auth.SessionUser;
import com.jungha.ShoppingMall.config.auth.SessionUser;
import com.jungha.ShoppingMall.service.posts.PostsService;
import com.jungha.ShoppingMall.web.dto.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user !=null){
            model.addAttribute("userName", user.getName());
        }

        System.out.println(principal);
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String update(Model model, @PathVariable Long id){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";

    }
}
