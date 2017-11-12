
 
package org.example.tariff.repositories;



import java.util.Date;
import java.util.List;
import org.example.tariff.entities.NotificationQuery;
import org.springframework.data.jpa.repository.JpaRepository;



public interface NotificationQueryRepository extends JpaRepository<NotificationQuery, Long>
{

	List<NotificationQuery> findByStartBefore(Date date);

}
