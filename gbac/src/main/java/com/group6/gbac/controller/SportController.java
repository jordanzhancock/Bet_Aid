package com.group6.gbac.controller;



import com.group6.gbac.model.Bookmaker;
import com.group6.gbac.model.Score;
import com.group6.gbac.model.Sport;
import com.group6.gbac.repository.SportRepository;
import com.group6.gbac.service.OddsAPIAdapter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SportController {

    //Sport sport = new Sport();
    @Autowired
    SportRepository sportRepository;


    OddsAPIAdapter oddsAPIAdapter = new OddsAPIAdapter();

    @GetMapping("/sport")
    public Object getSport() throws IOException {
        Object obj = oddsAPIAdapter.mockedSportResponse;

//        sportRepository.save(sport.get(0));
//        sportRepository.save(sport);
//        Object obj = mapper.readValue(responseStream,Object.class);
//
//        return new ResponseEntity<>(obj, HttpStatus.OK);
        return obj;
    }



    @GetMapping("/scores/nba")
    public ResponseEntity<String> getNBAScores() {
        // Parse the mocked response
//        List<Sport> sports = oddsAPIAdapter.parseSport(new JSONObject(oddsAPIAdapter.mockedScoreResponse));
        List<List<Score>> nbaScores = new ArrayList<>();
//        for(int i = 0; i < sports.size();i++){
////            nbaScores.add(sports.get(i));
////        }
        JSONArray sports = new JSONArray(oddsAPIAdapter.mockedScoreResponse);
//        for(int i = 0; i < sports.length(); i++){
//            JSONObject sport = (JSONObject) sports.get(i);
//            nbaScores.add(oddsAPIAdapter.parseScore(sport));
//       }

        // Save scores to the database (assuming sportRepository.save(score) is correctly implemented)

        // Return the NBA scores
        return new ResponseEntity<>(oddsAPIAdapter.mockedScoreResponse, HttpStatus.OK);
    }

    @GetMapping("/bookmaker/nba")
    public ResponseEntity<List<Bookmaker>> getNBABookmaker() {
        // Parse the mocked response
        List<Bookmaker> result = oddsAPIAdapter.parseBookmaker(new JSONObject(oddsAPIAdapter.mockedNbaOddsResponse));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/sports/basketball_nba/odds")
//    public ResponseEntity<List<Bet>> getNBAGameOdds(){
//
//
//    }

//@PostMapping("/scores/nba")
//    public ResponseEntity<List<Score>> postMockNBAScore(){
//    List<Score> result = oddsAPIAdapter.parseScore(new JSONObject(oddsAPIAdapter.mockedScoreResponse));
//        return new ResponseEntity<>(result,HttpStatus.OK);
//}



    // parseSport








}
