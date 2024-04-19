package com.group6.gbac.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.gbac.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.awt.print.Book;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OddsAPIAdapter {
    public List<Sport> findSport() throws IOException {
        URL url = new URL("https://api.the-odds-api.com/v4/sports/americanfootball_nfl/odds?regions=us&oddsFormat=american&apiKey=89b24348a53471d7084aef5307704b36");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        InputStream responseStream = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

        JsonObjectSerializer<Sport> serializer = mapper.readValue(responseStream, new TypeReference<JsonObjectSerializer<Sport>>() {
        });
        System.out.println("Serializer: " + serializer);
        JSONObject jsonObject = mapper.readValue(responseStream, new TypeReference<JSONObject>(){});
        List<Sport> sports = parseSport(jsonObject);

        return sports;
    }

    public List<Sport> mockedFindSport(){
        JSONObject jsonObject = new JSONObject(mockedSportResponse);
        List<Sport> sports = parseSport(jsonObject);
        return sports;
    }

    public List<Sport> parseSport(JSONObject jsonObject) {
        List<Sport> sports = new ArrayList<>();
        JSONArray sportArray = jsonObject.getJSONArray("sports");
        for (int i = 0; i < sportArray.length(); i++) {
            JSONObject sportObj = sportArray.getJSONObject(i);
            Sport sportModel = new Sport();
            sportModel.setId(sportObj.getString("id"));
            sportModel.setSportKey(sportObj.getString("sport_key"));
            sportModel.setSportTitle(sportObj.getString("sport_title"));
            sportModel.setCommenceTime( new Date(sportObj.getString("commence_time")));
            sportModel.setHomeTeam(sportObj.getString("home_team"));
            sportModel.setAwayTeam(sportObj.getString("away_team"));
            sports.add(sportModel);
        }
        return sports;
    }

    public List<Score> parseScore(JSONObject jsonObjectSport) {
        List<Score> scores = new ArrayList<>();
        // Check if scores is null or not
        if (!jsonObjectSport.isNull("scores")) {
            JSONArray scoreArray = jsonObjectSport.getJSONArray("scores");
            String sportId = jsonObjectSport.getString("id");
            try {
                for (int j = 0; j < scoreArray.length(); j++) {
                    JSONObject scoreObj = scoreArray.getJSONObject(j);
                    String scoreValue = scoreObj.optString("score", null);
                    String name = scoreObj.optString("name", null);
                    if (scoreValue != null && name != null) {
                        Score scoreModel = new Score();
                        scoreModel.setSport_Id(sportId);
                        scoreModel.setScore(scoreValue);
                        scoreModel.setName(name);
                        scores.add(scoreModel);
                    } else {
                        // Log a warning or handle the missing data appropriately
                        System.out.println("Invalid or missing score data in JSON object.");
                    }
                }
//                    } else {
//                        // Handle the case when "scores" is not an array
//                        System.out.println("Invalid format for scores in JSON object.");
//                    }
//                } else {
//                    // Handle the case when "scores" is null
//                    System.out.println("Scores are null for the JSON object.");
//                }
//            }
            } catch (JSONException e) {
                // Log or handle JSON parsing error
                e.printStackTrace();
            }
        }
        return scores;
    }


    private List<Market> parseMarket(JSONObject jsonObject){
        List<Market> markets = new ArrayList<>();
        String keyid = jsonObject.getString("key");
        JSONArray marketArray = jsonObject.getJSONArray("markets");
        for(int i =0; i < marketArray.length(); i++ ){
            JSONObject marketObj = marketArray.getJSONObject(i);
            String lastUpdate = marketObj.getString("last_update");
            Object outcome = marketObj.getInt("outcomes");
        }
        return markets;
    }
    public List<Bookmaker> parseBookmaker(JSONObject jsonObject){
        List<Bookmaker> bookmakers = new ArrayList<>();
        JSONArray bookmakerArray = jsonObject.getJSONArray("bookmakers");
        for(int i = 0; i < bookmakerArray.length(); i++){
            JSONObject bmObject = bookmakerArray.getJSONObject(i);
            Bookmaker bmModel = new Bookmaker();
            bmModel.setKey(bmObject.getString("key"));
            bmModel.setTitle(bmObject.getString("title"));
            bmModel.setLastUpdate(new Date(bmObject.getString("last_update")));
        }
    return bookmakers;
    }
    private List<Outcome> parseOutcome(JSONObject jsonObject){
        List<Outcome> outcomes = new ArrayList<>();
        JSONArray jOutcomeArray =  jsonObject.getJSONArray("outcomes");
        for(int i = 0; i < jOutcomeArray.length(); i++){
            JSONObject outcomeObj = jOutcomeArray.getJSONObject(i);
            Outcome outcomeModel = new Outcome();
            outcomeModel.setName(outcomeObj.getString("name"));
            outcomeModel.setPrice(outcomeObj.getInt("price"));
        }
        return outcomes;
    }




    public String mockedSportResponse = "[\n" +
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


    public String mockedScoreResponse = "[\n" +
            "    {\n" +
            "        \"id\": \"f13eb486120d8466fc3d004f69280112\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-17T23:16:00Z\",\n" +
            "        \"completed\": true,\n" +
            "        \"home_team\": \"Philadelphia 76ers\",\n" +
            "        \"away_team\": \"Miami Heat\",\n" +
            "        \"scores\": [\n" +
            "            {\n" +
            "                \"name\": \"Philadelphia 76ers\",\n" +
            "                \"score\": \"105\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"Miami Heat\",\n" +
            "                \"score\": \"104\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"last_update\": \"2024-04-18T09:33:08Z\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"c87df77d60f9110b769f8e3f4fcc83c6\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-18T01:49:24Z\",\n" +
            "        \"completed\": true,\n" +
            "        \"home_team\": \"Chicago Bulls\",\n" +
            "        \"away_team\": \"Atlanta Hawks\",\n" +
            "        \"scores\": [\n" +
            "            {\n" +
            "                \"name\": \"Chicago Bulls\",\n" +
            "                \"score\": \"131\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"Atlanta Hawks\",\n" +
            "                \"score\": \"116\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"last_update\": \"2024-04-18T09:33:08Z\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"63ee1b0647d6811b27076ac51f0278c4\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-19T23:10:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"Miami Heat\",\n" +
            "        \"away_team\": \"Chicago Bulls\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"fbfa8d9d77468594cb8519b1f5a26d36\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-20T01:40:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"New Orleans Pelicans\",\n" +
            "        \"away_team\": \"Sacramento Kings\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"0fd3e3ee6cadcae1adf05b0b3fcd1692\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-20T17:10:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"Cleveland Cavaliers\",\n" +
            "        \"away_team\": \"Orlando Magic\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"58fe88d816c30e8186084fd60e8bc1fe\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-20T19:30:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"Minnesota Timberwolves\",\n" +
            "        \"away_team\": \"Phoenix Suns\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"745945d0212e97788728dced917cea25\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-20T22:10:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"New York Knicks\",\n" +
            "        \"away_team\": \"Philadelphia 76ers\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"ef3b75810319700a88a1be226ccc7b50\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-21T00:40:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"Denver Nuggets\",\n" +
            "        \"away_team\": \"Los Angeles Lakers\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"7aa3ab404262a55d4c5473372324c52a\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-21T19:40:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"Los Angeles Clippers\",\n" +
            "        \"away_team\": \"Dallas Mavericks\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"7611315f3f28327571d9f58212774a99\",\n" +
            "        \"sport_key\": \"basketball_nba\",\n" +
            "        \"sport_title\": \"NBA\",\n" +
            "        \"commence_time\": \"2024-04-21T23:10:00Z\",\n" +
            "        \"completed\": false,\n" +
            "        \"home_team\": \"Milwaukee Bucks\",\n" +
            "        \"away_team\": \"Indiana Pacers\",\n" +
            "        \"scores\": null,\n" +
            "        \"last_update\": null\n" +
            "    }\n" +
            "]";


    public String mockedNbaOddsResponse = "[\n" +
            "  {\n" +
            "    \"id\": \"298f43b988d512301f1283f58465be55\",\n" +
            "    \"sport_key\": \"basketball_nba\",\n" +
            "    \"sport_title\": \"NBA\",\n" +
            "    \"commence_time\": \"2024-04-16T23:40:00Z\",\n" +
            "    \"home_team\": \"New Orleans Pelicans\",\n" +
            "    \"away_team\": \"Los Angeles Lakers\",\n" +
            "    \"bookmakers\": [\n" +
            "      {\n" +
            "        \"key\": \"draftkings\",\n" +
            "        \"title\": \"DraftKings\",\n" +
            "        \"last_update\": \"2024-04-15T17:29:23Z\",\n" +
            "        \"markets\": [\n" +
            "          {\n" +
            "            \"key\": \"h2h\",\n" +
            "            \"last_update\": \"2024-04-15T17:29:23Z\",\n" +
            "            \"outcomes\": [\n" +
            "              {\n" +
            "                \"name\": \"Los Angeles Lakers\",\n" +
            "                \"price\": -110\n" +
            "              },\n" +
            "              {\n" +
            "                \"name\": \"New Orleans Pelicans\",\n" +
            "                \"price\": -110\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        ]\n" +
            "      }";


}
