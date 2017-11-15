package org.example.tariff.repositories;





import java.util.Optional;
import org.example.tariff.entities.TariffDetails;
import org.springframework.data.repository.CrudRepository;


public interface TariffDetailsRepository extends CrudRepository<TariffDetails, Long>
{
    Optional<TariffDetails> findById(Long id);
	
}