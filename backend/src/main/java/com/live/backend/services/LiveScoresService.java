package com.live.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.live.backend.models.LiveScores;
import com.live.backend.repos.LiveScoresRepository;

@Service
public class LiveScoresService {

    @Autowired
    private LiveScoresRepository liveScoresRepository;

    public List<LiveScores> findAll() {
        return liveScoresRepository.findAll();
    }

    public Optional<LiveScores> findById(Long id) {
        return liveScoresRepository.findById(id);
    }

    public LiveScores save(LiveScores liveScores) {
        liveScores.setCreated_at(LocalDateTime.now());
        liveScores.setUpdated_at(LocalDateTime.now());
        return liveScoresRepository.save(liveScores);
    }

    public LiveScores updateLiveScores(Long id, LiveScores liveScores) {
        Optional<LiveScores> existingLiveScoresOpt = liveScoresRepository.findById(id);
        if (existingLiveScoresOpt.isPresent()) {
            LiveScores existingLiveScores = existingLiveScoresOpt.get();
            existingLiveScores.setUpdated_at(LocalDateTime.now());

            return liveScoresRepository.save(existingLiveScores);
        } else {
            throw new RuntimeException("LiveScores not found with ID: " + id);
        }
    }

    public void deleteById(Long id) {
        liveScoresRepository.deleteById(id);
    }
}
