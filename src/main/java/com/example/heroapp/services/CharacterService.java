package com.example.heroapp.services;

import com.example.heroapp.models.CharacterEntity;
import com.example.heroapp.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repo) {
        repository = repo;
    }

    public CharacterEntity add(String name, String type) {
        CharacterEntity c = new CharacterEntity();
        c.setName(name);
        c.setType(type);
        return repository.save(c);
    }

    public List<CharacterEntity> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
