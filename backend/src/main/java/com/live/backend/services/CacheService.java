package com.live.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.live.backend.models.Cache;
import com.live.backend.repos.CacheRepository;

@Service
public class CacheService {

    @Autowired
    private CacheRepository cacheRepository;

    public List<Cache> getAllCacheEntries() {
        return cacheRepository.findAll();
    }

    public Optional<Cache> getCacheEntryByKey(String key) {
        return cacheRepository.findById(key);
    }

    public Cache createCacheEntry(Cache cache) {
        return cacheRepository.save(cache);
    }

    public Cache updateCacheEntry(String key, Cache updatedCache) {
        Optional<Cache> existingCache = cacheRepository.findById(key);
        if (existingCache.isPresent()) {
            Cache cache = existingCache.get();
            cache.setValue(updatedCache.getValue());
            cache.setExpiration(updatedCache.getExpiration());
            return cacheRepository.save(cache);
        } else {
            throw new RuntimeException("Cache entry not found with key: " + key);
        }
    }

    public void deleteCacheEntry(String key) {
        cacheRepository.deleteById(key);
    }
}
