package com.codingdojo.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.lookify.models.Song;
import com.codingdojo.lookify.repositories.SongRepository;

@Service
public class SongService {
	
	@Autowired
	SongRepository songRepository;
	
	//return all songs
		public List<Song> allSongs(){
			return songRepository.findAll();
		}
		

	//Create a song
		public Song createSong(Song song) {
			return songRepository.save(song);
		}
		
	//Update a song
		public Song updateSong(Song song) {
			return songRepository.save(song);
		}
		
	//find a song by id
		public Song findSong(Long id) {
			Optional<Song> song = songRepository.findById(id);
			if(song.isPresent()) {
				return song.get();
			} else {
				return null;
			}
		}
		
	//Delete a song
		public void deleteSong(Long id) {
			Optional<Song> song = songRepository.findById(id);
			if(song.isPresent()) {
				songRepository.deleteById(id);
			}
		}
		
	//find songs containing an artist
		public List<Song> songsContainingArtist(String artist){
		return songRepository.findByArtistContaining(artist);
		}
		
	//find top ten songs
		public List<Song> topTen() {
			return songRepository.findTop10ByOrderByRatingDesc();
		}
}
