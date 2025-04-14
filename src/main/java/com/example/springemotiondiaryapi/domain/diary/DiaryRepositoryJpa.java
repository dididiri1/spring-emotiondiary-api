package com.example.springemotiondiaryapi.domain.diary;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepositoryJpa extends JpaRepository<Diary, Long> {
}
