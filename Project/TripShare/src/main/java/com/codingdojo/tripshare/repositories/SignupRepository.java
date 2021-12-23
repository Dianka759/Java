package com.codingdojo.tripshare.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.tripshare.models.Signup;

@Repository
public interface SignupRepository extends CrudRepository <Signup, Long> {

	List<Signup> findAll();
//	Optional<Signup> findByUser_idAndFriend_id(Long user_id, Long friend_id);
}
