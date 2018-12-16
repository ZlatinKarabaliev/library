package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
