package com.juan.bookclub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juan.bookclub.models.Author;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {
	List<Author> findAll();
}
