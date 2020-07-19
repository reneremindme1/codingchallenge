package com.fazekas.coding_challenge.controller;

import com.fazekas.coding_challenge.exception.ResourceNotFoundException;
import com.fazekas.coding_challenge.model.GitRepo;
import com.fazekas.coding_challenge.service.GitRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitRepoController {

    @Autowired
    GitRepoService gitRepoService;

    @GetMapping(value = "/users/{id}/repositories")
    public ResponseEntity<List<GitRepo>> getGitReposForUser(
            @PathVariable Long id
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(gitRepoService.getGitReposForUser(id));
    }
}
