package com.codingdojo.tripshare.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.tripshare.models.Friendship;
import com.codingdojo.tripshare.models.User;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
	
	List<Friendship> findAll();
	Optional<Friendship> findByUser(Long user_id);
	List<Friendship> deleteByUserAndFriend(Long user_id, Long friend_id);

}
