package com.live.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.live.backend.models.LiveScores;
import com.live.backend.services.LiveScoresService;

@RestController
@RequestMapping("/api/live-scores")
public class LiveScoresController {

    @Autowired
    private LiveScoresService liveScoresService;

    @GetMapping
    public ResponseEntity<List<LiveScores>> getAllLiveScores() {
        List<LiveScores> liveScores = liveScoresService.findAll();
        return new ResponseEntity<>(liveScores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiveScores> getLiveScoreById(@PathVariable Long id) {
        Optional<LiveScores> liveScores = liveScoresService.findById(id);
        return liveScores.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<LiveScores> createLiveScore(@RequestBody LiveScores liveScores) {
        LiveScores savedLiveScore = liveScoresService.save(liveScores);
        return new ResponseEntity<>(savedLiveScore, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LiveScores> updateLiveScore(@PathVariable Long id, @RequestBody LiveScores liveScores) {
        try {
            LiveScores updatedLiveScore = liveScoresService.updateLiveScores(id, liveScores);
            return new ResponseEntity<>(updatedLiveScore, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLiveScore(@PathVariable Long id) {
        liveScoresService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
