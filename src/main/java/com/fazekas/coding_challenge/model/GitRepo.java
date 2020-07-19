package com.fazekas.coding_challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Data
@ToString
public class GitRepo {

    @JsonProperty("html_url")
    private String htmlUrl;
    private String language;
}
