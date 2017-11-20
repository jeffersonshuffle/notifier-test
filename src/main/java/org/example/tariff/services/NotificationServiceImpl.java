package org.example.tariff.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.example.tariff.entities.NotificationQuery;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.example.tariff.entities.PriceChangeReport;

import org.example.tariff.exceptions.NotificationProcessException;
import org.example.tariff.model.NotificationDTO;
import org.example.tariff.model.NotifyRequest;
import org.example.tariff.repositories.NotificationQueryRepository;
import org.example.tariff.repositories.NotificationRepository;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;




@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    
    
	
    NotificationRepository notificationRepository;
        
    NotificationQueryRepository queryRepository;
    NotificationBuilder  notificationBuilder;    
    
    @Autowired
    public void setNotificationBuilder(NotificationBuilder notificationBuilder) {
        this.notificationBuilder = notificationBuilder;
    }
   
    
    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    @Autowired
    public void setQueryRepository( NotificationQueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }
    
   
        
	
       
        
        
        @Transactional( readOnly= true ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
    @Override
        public NotificationDTO processRequestNotification(final NotificationType notificationType,NotifyRequest request)
                throws NotificationProcessException
        {
            if(request.getStartOfPeriod().compareTo(new Date())<=0){
                PriceChangeReport r=notificationRepository.findByTariffId( request.getTariff().getId());
                switch(notificationType){
                    case TEMPLATE:
                        return this.notificationBuilder
                                .generateNotificationTemplate(r);
                    case MESSAGE:   
                        return this.notificationBuilder
                                .generateNotification(r);
                }   
            }
            tryPutNotification( request);
            return NotificationDTO.getEmty();
        }
        
        @Transactional( readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
        void tryPutNotification(NotifyRequest request)
                throws NotificationProcessException
        {
            try{
                NotificationQuery n=new NotificationQuery(0L, request.getTariff().getId()
                    , request.getUser().getId(), request.getStartOfPeriod(), request.getEndOfPeriod());
                queryRepository.save(n);
            }
            catch(Throwable ex){
                throw new NotificationProcessException(ex);
            }
        }
        
        @Scheduled(fixedDelay=10000)
        @Async
        @Transactional( readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
    @Override
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
