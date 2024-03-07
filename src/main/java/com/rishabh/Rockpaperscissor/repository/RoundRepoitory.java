package com.rishabh.Rockpaperscissor.repository;

import com.rishabh.Rockpaperscissor.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepoitory extends JpaRepository<Round,Long> {
}
