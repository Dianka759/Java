package com.codingdojo.tripshare.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.tripshare.models.Trip;
import com.codingdojo.tripshare.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    
    List<User> findByParticipatingNotContains(Trip trip);
    List<User> findByParticipatingOrMyFriendsNotContains(Trip trip, User user);
}
