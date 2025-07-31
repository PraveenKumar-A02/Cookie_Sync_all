package com.example.Cksync.repository;

import com.example.Cksync.model.CookieMatch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CookieMatchingRepo extends ElasticsearchRepository<CookieMatch, String> {
    CookieMatch findByMroSyncId(String mroSyncId);
}