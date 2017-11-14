package org.example.tariff.services;
import java.util.Date;
import java.util.List;
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
        public NotificationDTO processRequestNotification(int notificationType,NotifyRequest request){
            if(request.getStartOfPeriod().compareTo(new Date())<=0)
                if(notificationType==0)
                    return getNotificationMessageFor(request.getTariff().getId());
                else if(notificationType==1)
                    return getNotificationTemplateFor(request.getTariff().getId());
            
            NotificationQuery n=new NotificationQuery(0L, request.getTariff().getId()
                    , request.getUser().getId(), request.getStartOfPeriod(), request.getEndOfPeriod());
            queryRepository.save(n);
            //sendNotifications();
            return null;
        }
        
        @Scheduled(fixedDelay=10000)
        @Transactional( readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
	public void sendNotifications() {
            List<NotificationQuery> queries= queryRepository.findByStartBefore(new Date());
            if(queries==null)return;
            processSend(queries);
	}
        
        // mock for notification queue processing
        // just delete
        void processSend(List<NotificationQuery> queries){
            queries.stream().forEach(q->queryRepository.delete(q.getId()));
        }

}
