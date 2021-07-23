package com.example.wordgame;

import com.example.wordgame.controllers.GameController;
import com.example.wordgame.entitities.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    private ObjectMapper objectMapper = new MappingJackson2HttpMessageConverter().getObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCase1() throws Exception {
        Game game = new Game();
        game.setWords(toList(new String[] {"fish", "horse", "egg", "goose", "eagle"}));
        Game resultGame = new Game();
        resultGame.setWords(toList(new String[] {"fish", "horse", "egg", "goose", "eagle"}));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/play/").content(objectMapper.writeValueAsString(game))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String outputWords = result.getResponse().getContentAsString();
        String expectedOutputWords = objectMapper.writeValueAsString(resultGame);
        Assert.assertEquals(outputWords, expectedOutputWords);
    }

    @Test
    public void testCase2() throws Exception {
        Game game = new Game();
        game.setWords(toList(new String[] {"fish", "horse", "snake", "goose", "eagle"}));
        Game resultGame = new Game();
        resultGame.setWords(toList(new String[] {"fish", "horse"}));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/play/").content(objectMapper.writeValueAsString(game))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String outputWords = result.getResponse().getContentAsString();
        String expectedOutputWords = objectMapper.writeValueAsString(resultGame);
        Assert.assertEquals(outputWords, expectedOutputWords);
    }

    @Test
    public void testCase3() throws Exception {
        Game game = new Game();
        game.setWords(toList(new String[] {"fish", "horse", "", "goose", "eagle"}));
        Game resultGame = new Game();
        resultGame.setWords(toList(new String[] {"fish", "horse"}));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/play/").content(objectMapper.writeValueAsString(game))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String outputWords = result.getResponse().getContentAsString();
        String expectedOutputWords = objectMapper.writeValueAsString(resultGame);
        Assert.assertEquals(outputWords, expectedOutputWords);
    }

    @Test
    public void testCase4() throws Exception {
        Game game = new Game();
        game.setWords(toList(new String[] {"", "horse", "", "goose", "eagle"}));
        Game resultGame = new Game();
        resultGame.setWords(toList(new String[] {}));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/play/").content(objectMapper.writeValueAsString(game))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String outputWords = result.getResponse().getContentAsString();
        String expectedOutputWords = objectMapper.writeValueAsString(resultGame);
        Assert.assertEquals(outputWords, expectedOutputWords);
    }

    private static List<String> toList(String[] string) {
        List<String> result = new ArrayList<>();
        for(String s:string) {
            result.add(s);
        }
        return result;
    }


}
