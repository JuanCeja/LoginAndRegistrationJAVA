package com.juan.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.bookclub.models.Author;
import com.juan.bookclub.repositories.AuthorRepo;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepo authorRepo;
	
//	READ ALL
	public List<Author> allAuthors(){
		 return authorRepo.findAll();
	}
	
//	CREATE
	public Author createdAuthor(Author author) {
		return authorRepo.save(author);
	}
	
//	READ ONE
	public Author findAuthor(Long id) {
		Optional<Author> optionalAuthor = authorRepo.findById(id);
		if(optionalAuthor.isPresent()) {
			return optionalAuthor.get();
		} else {
			return null;
		}
	}
	
//	UPDATE
	public Author updatedAuthor (Author author) {
		return authorRepo.save(author);
	}
	
//	DELETE
	public void deleteAuthor (Long id) {
		authorRepo.deleteById(id);
	}
}
