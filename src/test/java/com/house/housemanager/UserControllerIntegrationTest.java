package com.house.housemanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.housemanager.user.User;
import com.house.housemanager.user.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Arrange
        User user1 = new User();
        user1.setFirstname("John");
        user1.setLastname("Doe");
        User user2 = new User();
        user2.setFirstname("Jane");
        user2.setLastname("Smith");
        userRepository.save(user1);
        userRepository.save(user2);

        // Act & Assert
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[0].lastname").value("Doe"))
                .andExpect(jsonPath("$[1].firstname").value("Jane"))
                .andExpect(jsonPath("$[1].lastname").value("Smith"));
    }

    @Test
    void testGetUserById() throws Exception {
        // Arrange
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user = userRepository.save(user);

        // Act & Assert
        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"));
    }

    @Test
    void testAddNewUser() throws Exception {
        // Arrange
        User newUser = new User();
        newUser.setFirstname("John");
        newUser.setLastname("Doe");
        String userJson = objectMapper.writeValueAsString(newUser);

        // Act
        MvcResult result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        User createdUser = objectMapper.readValue(content, User.class);
        assertThat(createdUser.getFirstname()).isEqualTo("John");
        assertThat(createdUser.getLastname()).isEqualTo("Doe");

        assertThat(userRepository.findById(createdUser.getId())).isPresent();
    }
}
