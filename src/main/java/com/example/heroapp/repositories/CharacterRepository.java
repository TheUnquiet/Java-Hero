package com.example.heroapp.repositories;

import com.example.heroapp.models.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
