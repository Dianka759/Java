package com.codingdojo.lookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
	// this method retrieves all the languages from the database
	List<Song> findAll();
	
	// find artist based on certain criteria
	List<Song> findByArtistContaining(String search); 
	
	// query top 10 by rating
	List<Song> findTop10ByOrderByRatingDesc(); 
}