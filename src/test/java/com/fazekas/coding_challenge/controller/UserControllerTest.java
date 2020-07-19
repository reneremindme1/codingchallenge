package com.fazekas.coding_challenge.controller;

import com.fazekas.coding_challenge.model.User;
import com.fazekas.coding_challenge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    UserController controller;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllUsers() throws Exception {

        User user = new User();
        List<User> users = Arrays.asList(user);

        given(userRepository.findAll()).willReturn(users);

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getUser() throws Exception {
        User user = new User();
        user.setId(1L);

        given(userRepository.findById(1L)).willReturn(java.util.Optional.of(user));

        mvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setId(1L);

        given(userRepository.save(user)).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setId(1L);

        given(userRepository.findById(1L)).willReturn(java.util.Optional.of(user));
        given(userRepository.save(user)).willReturn(user);

        mvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void deleteUser() throws Exception {
        mvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}