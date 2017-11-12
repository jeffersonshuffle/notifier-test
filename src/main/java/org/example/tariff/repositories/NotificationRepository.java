package org.example.tariff.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.example.tariff.entities.PriceChangeReport;


public interface NotificationRepository extends JpaRepository<PriceChangeReport, Long>
{

	PriceChangeReport findByTariffId(Long tariffId);

}
