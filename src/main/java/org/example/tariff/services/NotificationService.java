package org.example.tariff.services;
import java.util.Date;
import java.util.List;
import org.example.tariff.entities.NotificationQuery;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.example.tariff.entities.PriceChangeReport;
import org.example.tariff.model.NotificationDTO;
import org.example.tariff.model.NotifyRequest;
import org.example.tariff.repositories.NotificationQueryRepository;
import org.example.tariff.repositories.NotificationRepository;
import org.example.tariff.utils.BeanCopyUtil;




@Service
@Transactional
public class NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
        @Autowired
        NotificationQueryRepository queryRepository;
        
        
	@Transactional( readOnly= true ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
	public NotificationDTO getNotificationFor(Long tariffId) {
		 PriceChangeReport r=notificationRepository.findByTariffId( tariffId);
                 return BeanCopyUtil.toNotificationDTO(r)
                                    .generateNotificationTemplate()
                                    .generateNotification();
	}
        
        
        @Transactional( readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
        public NotificationDTO processRequestNotification(NotifyRequest request){
            if(request.getStartOfPeriod().compareTo(new Date())<=0)
                return getNotificationFor(request.getTariff().getId());
            NotificationQuery n=new NotificationQuery(0L, request.getTariff().getId()
                    , request.getUser().getId(), request.getStartOfPeriod(), request.getEndOfPeriod());
            queryRepository.save(n);
            sendNotifications();
            return null;
        }
        
        
        @Transactional( readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
	public void sendNotifications() {
                List<NotificationQuery> queries= queryRepository.findByStartBefore(new Date());
		if(queries==null)return;
                queries.stream().forEach(q->queryRepository.delete(q.getId()));
	}

}
