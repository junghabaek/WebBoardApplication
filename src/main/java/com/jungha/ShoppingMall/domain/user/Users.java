package com.jungha.ShoppingMall.domain.user;

import com.jungha.ShoppingMall.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    @PrePersist
//    public void prePersist(){
//        this.name="정하";
//        this.email="jungha@naver.com";
//
//    }

    @Builder
    public Users(String name, String email, String picture, Role role){
        this.name = name;
        this.email= email;
        this.picture=picture;
        this.role = role;

    }

    public Users update (String name, String picture){
        this.name= name;
        this.picture=picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}

