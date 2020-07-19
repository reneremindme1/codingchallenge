package com.fazekas.coding_challenge.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private String gitHubProfileUrl;
}
