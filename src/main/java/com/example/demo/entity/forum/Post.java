package com.example.demo.entity.forum;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Topic topic;

    private LocalDateTime creationDate;

    public Post() {
        this.creationDate = LocalDateTime.now();
    }
}
