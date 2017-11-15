
 
package org.example.tariff.repositories;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.example.tariff.entities.NotificationQuery;
import org.springframework.data.jpa.repository.JpaRepository;



public interface NotificationQueryRepository extends JpaRepository<NotificationQuery, Long>
{

	Optional<List<NotificationQuery>> findByStartBefore(Date date);

}
