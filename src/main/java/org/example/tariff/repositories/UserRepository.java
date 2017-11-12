package org.example.tariff.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.example.tariff.entities.User;


public interface UserRepository extends JpaRepository<User, Long>
{

	
}
