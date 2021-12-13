package com.codingdojo.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.lookify.models.Song;
import com.codingdojo.lookify.services.SongService;

@Controller
public class HomeController {
	
	@Autowired
	SongService songService;

	@RequestMapping("/")
	public String welcome() {
		return "redirect:/lookify";
	}
	
	@RequestMapping("/lookify")
	public String index() {
		return "index.jsp";
	}
	
	@RequestMapping("/lookify/dashboard")
	public String dashboard(Model model, @ModelAttribute("song") Song song) {
		List<Song> songs = songService.allSongs();
		model.addAttribute("songs", songs);
		return "dashboard.jsp";
	}
	
	@RequestMapping("/lookify/song/new")
	public String newSong(@ModelAttribute("song") Song song) {
		return "newSong.jsp";
	}
	
	@PostMapping("/lookify/newSong")
	public String createNewSong(@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if(result.hasErrors()) {
			return "newSong.jsp";
		} else {
			songService.createSong(song);
			return "redirect:/lookify/dashboard";
		}
	}
	
	@RequestMapping("/lookify/song/edit/{id}")
	public String editSong(@PathVariable("id") long id,	Model model) {
		Song song = songService.findSong(id);
		model.addAttribute("song", song);
		return "edit.jsp";
	}
	
	@PostMapping(value="/lookify/updateSong/{id}")
	public String updateSong(@PathVariable("id") long id, 
			@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}
		else {
			songService.updateSong(song);
			return "redirect:/lookify/song/" +id;
		}
	}
	
	@RequestMapping("/lookify/song/{id}")
	public String showSong(@PathVariable("id") Long id, Model model) {
		Song song = songService.findSong(id);
		model.addAttribute("song", song);
		return "showSong.jsp";
	}
	
	@RequestMapping("/lookify/song/delete/{id}")
	public String deleteSong(@PathVariable("id") Long id) {
		songService.deleteSong(id);
		return "redirect:/lookify/dashboard";
	}
	
	@RequestMapping("/lookify/search")
	public String search(@RequestParam("artist") String artist, Model model,
						 RedirectAttributes error) {
		List<Song> songs = songService.songsContainingArtist(artist);
		//if there is no artist, stay on dashboard and return an error
		if(artist == "") {
			error.addFlashAttribute("error", "Search field empty!");
			return "redirect:/lookify/dashboard";
		//if the artist search returns an empty list of songs, erorr, no such artist
		} else if (songs.isEmpty()) {
		error.addFlashAttribute("error", "Sorry, no such artist!");
		return "redirect:/lookify/dashboard";
		//else, just return the songs by the artist
		} else {
		model.addAttribute("artist", artist);
		model.addAttribute("songs", songs);
		return "search.jsp";
		}
	}
	
	@RequestMapping("/lookify/search/topTen")
	public String topTen(Model model) {
		List<Song> songs = songService.topTen();
		model.addAttribute("songs", songs);
		return "topTen.jsp";
	}
}
