package com.group6.gbac.controller;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;


import com.group6.gbac.model.Score;
import com.group6.gbac.model.Sport;
import com.group6.gbac.repository.SportRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SportController {

    //Sport sport = new Sport();
    @Autowired
    SportRepository sportRepository;

    @GetMapping("/sport")
    public Object getSport() throws IOException {
        Object obj = mockedResponse;
//        URL url = new URL("https://api.the-odds-api.com/v4/sports/americanfootball_nfl/odds?regions=us&oddsFormat=american&apiKey=89b24348a53471d7084aef5307704b36");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        InputStream responseStream = con.getInputStream();
//        ObjectMapper mapper = new ObjectMapper();
//
//        JsonObjectSerializer<Sport> serializer = mapper.readValue(responseStream, new TypeReference<JsonObjectSerializer<Sport>>() {
//        });
//        System.out.println("Serializer: " + serializer);
//        JSONObject jsonObject = mapper.readValue(responseStream, new TypeReference<JSONObject>(){});
//        Sport sport = (Sport) jsonObject.get();
//        List<Sport> sports = mapper.readValue(responseStream, new TypeReference<List<Sport>>() {});
//        sportRepository.save(sport.get(0));
//        sportRepository.save(sport);
//        Object obj = mapper.readValue(responseStream,Object.class);
//
//        return new ResponseEntity<>(obj, HttpStatus.OK);
        return obj;
    }

    String mockedResponse = "[\n" +
            "    {\n" +
            "        \"id\": \"eca3b71919531e7ae0b4f3f501157e6c\",\n" +
            "        \"sport_key\": \"americanfootball_nfl\",\n" +
            "        \"sport_title\": \"NFL\",\n" +
            "        \"commence_time\": \"2024-09-06T23:00:00Z\",\n" +
            "        \"home_team\": \"Philadelphia Eagles\",\n" +
            "        \"away_team\": \"Green Bay Packers\",\n" +
            "        \"bookmakers\": [\n" +
            "            {\n" +
            "                \"key\": \"draftkings\",\n" +
            "                \"title\": \"DraftKings\",\n" +
            "                \"last_update\": \"2024-04-10T23:24:42Z\",\n" +
            "                \"markets\": [\n" +
            "                    {\n" +
            "                        \"key\": \"h2h\",\n" +
            "                        \"last_update\": \"2024-04-10T23:24:42Z\",\n" +
            "                        \"outcomes\": [\n" +
            "                            {\n" +
            "                                \"name\": \"Green Bay Packers\",\n" +
            "                                \"price\": -102\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"name\": \"Philadelphia Eagles\",\n" +
            "                                \"price\": -118\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"fanduel\",\n" +
            "                \"title\": \"FanDuel\",\n" +
            "                \"last_update\": \"2024-04-10T23:26:56Z\",\n" +
            "                \"markets\": [\n" +
            "                    {\n" +
            "                        \"key\": \"h2h\",\n" +
            "                        \"last_update\": \"2024-04-10T23:26:56Z\",\n" +
            "                        \"outcomes\": [\n" +
            "                            {\n" +
            "                                \"name\": \"Green Bay Packers\",\n" +
            "                                \"price\": -102\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"name\": \"Philadelphia Eagles\",\n" +
            "                                \"price\": -116\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "]";

    @GetMapping("/scores/nba")
    public String getNBAScores() {
        // Parse the mocked response
        //List<Score> nbaScores = parseMockedResponse();
       String obj = mockedScoreResponse;

        // Save scores to the database (assuming sportRepository.save(score) is correctly implemented)

        // Return the NBA scores
       // return new ResponseEntity<>(nbaScores, HttpStatus.OK);
        return obj;
    }
    String mockedScoreResponse = "{\n" +
            "    \"id\": \"e2296d6d1206f8d185466876e2b444ea\",\n" +
            "    \"sport_key\": \"basketball_nba\",\n" +
            "    \"sport_title\": \"NBA\",\n" +
            "    \"commence_time\": \"2022-02-06T03:11:26Z\",\n" +
            "    \"completed\": true,\n" +
            "    \"home_team\": \"Portland Trail Blazers\",\n" +
            "    \"away_team\": \"Milwaukee Bucks\",\n" +
            "    \"scores\": [\n" +
            "        {\n" +
            "            \"name\": \"Portland Trail Blazers\",\n" +
            "            \"score\": \"108\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Milwaukee Bucks\",\n" +
            "            \"score\": \"137\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private List<Score> parseMockedResponse() {


        // Parse the JSON response and extract scores
        List<Score> scores = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(mockedScoreResponse);
        JSONArray scoresArray = jsonObject.getJSONArray("scores");
        for (int i = 0; i < scoresArray.length(); i++) {
            JSONObject scoreObj = scoresArray.getJSONObject(i);
            String teamName = scoreObj.getString("name");
            int score = scoreObj.getInt("score");

            // Create a Score object and add it to the list
            Score scoreModel = new Score();
            scoreModel.setTeamName(teamName);
            scoreModel.setScore(String.valueOf(score));
            scores.add(scoreModel);
        }
        return scores;
    }


}
