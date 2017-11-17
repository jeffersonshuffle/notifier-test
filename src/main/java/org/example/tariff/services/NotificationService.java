package org.example.tariff.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.example.tariff.config.NotificationProperties;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;




@Service
@Transactional
public class NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
        @Autowired
        NotificationQueryRepository queryRepository;
         @Autowired
        NotificationProperties notificationTemplate;
        
	@Transactional( readOnly= true ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
	public NotificationDTO getNotificationTemplateFor(Long tariffId) {
		 PriceChangeReport r=notificationRepository.findByTariffId( tariffId);
                 return BeanCopyUtil.toNotificationDTO(r)
                                .initNotificationTemplate(notificationTemplate)
                                .generateNotificationTemplate();
	}
        public NotificationDTO getNotificationMessageFor(Long tariffId) {
		 PriceChangeReport r=notificationRepository.findByTariffId( tariffId);
                 return BeanCopyUtil.toNotificationDTO(r)
                         .initNotificationTemplate(notificationTemplate)
                         .generateNotification();
	}
        
        @Transactional( readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
        public NotificationDTO processRequestNotification(String notificationType,NotifyRequest request){
            if(request.getStartOfPeriod().compareTo(new Date())<=0)
                if(notificationType.equals("t"))
                    return getNotificationTemplateFor(request.getTariff().getId());
                else 
                    return getNotificationMessageFor(request.getTariff().getId());
                    
            
            NotificationQuery n=new NotificationQuery(0L, request.getTariff().getId()
                    , request.getUser().getId(), request.getStartOfPeriod(), request.getEndOfPeriod());
            queryRepository.save(n);
            
            return NotificationDTO.getEmty();
        }
        
        @Scheduled(fixedDelay=10000)
        @Async
        @Transactional( readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
	public void sendNotifications() {
            Optional<List<NotificationQuery>> queries= queryRepository.findByStartBefore(new Date());
            
            queries.ifPresent(q->processSend(q));
            
	}
        
        // mock for notification queue processing
        // just delete
        @Async
        void processSend(List<NotificationQuery> queries){
            queryRepository.deleteInBatch(queries);
            
        }

}
