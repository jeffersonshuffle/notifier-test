package org.example.tariff.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.example.tariff.entities.Tariff;


public interface TariffRepository extends JpaRepository<Tariff, Long>
{

	
}
