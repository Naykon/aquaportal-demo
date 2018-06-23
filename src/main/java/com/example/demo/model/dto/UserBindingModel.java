package com.example.demo.model.dto;

import com.example.demo.validation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserBindingModel {
    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @Column(unique = true)
    @Size(min = 5, max = 20)
    private String username;

    @Column(unique = true)
    @ValidEmail
    @NotNull
    @NotEmpty
    private String emailAddress;

    @JsonIgnore
    private String password;
}
