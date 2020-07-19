package com.fazekas.coding_challenge.service;

import com.fazekas.coding_challenge.exception.ResourceNotFoundException;
import com.fazekas.coding_challenge.model.GitRepo;
import com.fazekas.coding_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GitRepoService {

    public static final String API_URI = "https://api.github.com";

    @Autowired
    DefaultWebClient client;

    @Autowired
    UserRepository userRepository;

    public List<GitRepo> getGitReposForUser(Long id) throws ResourceNotFoundException {

        String gitProfileName = getGitProfileName(id);

        GitRepo[] gitRepo = client.getWebClient(API_URI)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/"+gitProfileName+"/repos")
                        //.queryParam("symbols", targetCurrency)
                        .build()
                )
                .retrieve()
                .bodyToMono(GitRepo[].class)
                .log()
                .block();

        return Arrays.asList(gitRepo);
    }

    private String getGitProfileName(Long id) throws ResourceNotFoundException {
        String gitHubProfileUrl = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found " + id)).getGitHubProfileUrl();
        return gitHubProfileUrl.substring(gitHubProfileUrl.lastIndexOf("/") + 1);
    }
}
