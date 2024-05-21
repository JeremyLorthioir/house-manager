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
import com.house.housemanager.enums.Period;
import com.house.housemanager.recurrence.Recurrence;
import com.house.housemanager.recurrence.RecurrenceRepository;
import com.house.housemanager.user.User;
import com.house.housemanager.user.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class RecurrenceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecurrenceRepository recurrenceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        recurrenceRepository.deleteAll();
    }

    @Test
    void testGetAllRecurrences() throws Exception {
        // Arrange
        Recurrence recurrence1 = new Recurrence();
        recurrence1.setName("Once");
        recurrence1.setDescription("Once in a day");
        recurrence1.setFrequency(1);
        recurrence1.setPeriod(Period.JOUR);
        Recurrence recurrence2 = new Recurrence();
        recurrence2.setName("Twice");
        recurrence2.setDescription("Twice in a day");
        recurrence2.setFrequency(2);
        recurrence2.setPeriod(Period.JOUR);
        recurrenceRepository.save(recurrence1);
        recurrenceRepository.save(recurrence2);

        // Act & Assert
        mockMvc.perform(get("/recurrences"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Once"))
                .andExpect(jsonPath("$[0].description").value("Once in a day"))
                .andExpect(jsonPath("$[0].frequency").value(1))
                .andExpect(jsonPath("$[0].period").value("JOUR"))
                .andExpect(jsonPath("$[1].name").value("Twice"))
                .andExpect(jsonPath("$[1].description").value("Twice in a day"))
                .andExpect(jsonPath("$[1].frequency").value(2))
                .andExpect(jsonPath("$[1].period").value("JOUR"));
    }

    @Test
    void testGetRecurrenceById() throws Exception {
        // Arrange
        Recurrence recurrence = new Recurrence();
        recurrence.setName("Once");
        recurrence.setDescription("Once in a day");
        recurrence.setFrequency(1);
        recurrence.setPeriod(Period.JOUR);
        recurrenceRepository.save(recurrence);

        // Act & Assert
        mockMvc.perform(get("/recurrences/"+recurrence.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Once"))
                .andExpect(jsonPath("$.description").value("Once in a day"))
                .andExpect(jsonPath("$.frequency").value(1))
                .andExpect(jsonPath("$.period").value("JOUR"));
    }

    @Test
    void testAddNewRecurrence() throws Exception {
        // Arrange
        Recurrence recurrence = new Recurrence();
        recurrence.setName("Once");
        recurrence.setDescription("Once in a day");
        recurrence.setFrequency(1);
        recurrence.setPeriod(Period.JOUR);
        String reccurenceJson = objectMapper.writeValueAsString(recurrence);

        // Act
        MvcResult result = mockMvc.perform(post("/recurrences")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reccurenceJson))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        Recurrence createdRecurrence = objectMapper.readValue(content, Recurrence.class);
        assertThat(createdRecurrence.getName()).isEqualTo("Once");
        assertThat(createdRecurrence.getDescription()).isEqualTo("Once in a day");
        assertThat(createdRecurrence.getFrequency()).isEqualTo(1);
        assertThat(createdRecurrence.getPeriod()).isEqualTo(Period.JOUR);

        assertThat(recurrenceRepository.findById(createdRecurrence.getId())).isPresent();
    }
}
