package com.example.finalproject.person.entity;

import com.example.finalproject.person.dto.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, unique = true )
    private String lastName;

    @Column(nullable = false)
    private String email;

    public UserResponse mapToDto(){
        return new UserResponse(this.id,this.firstName,this.lastName,this.email);
    }

    public User(String firstName, String lastName ,String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }

}
