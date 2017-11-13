package org.example.tariff.web.controllers;

 

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import org.example.tariff.model.*;

import org.example.tariff.services.NotificationService;
import org.example.tariff.services.TariffService;
import org.example.tariff.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;







@RestController
@RequestMapping(value="/api-tariff-notifier")
public class TariffController
{
	
	
	@Autowired
	private TariffService tariffService;
	@Autowired
	private UserService userService;
	@Autowired
	private NotificationService notificationService;
        
        @RequestMapping(value="/", method=RequestMethod.GET)
        public String home(){
            StringBuilder sb=new StringBuilder();
            sb.append("<p>API Description</p>")
                    .append("<p>/api-tariff-notifier  is app base address</p>")
              .append("<p>/test{id} METOD GET PARAMS id - fuction for randomly update details of tariff with {id}</p>")
                    .append("<p>/users METOD GET  PARAMS page=1 size=5 -  list of all users in db</p>" )
                    .append("<p>/tariffs METOD GET  PARAMS page=1 size=5 -  list of all tariffs with details in db</p>")
                    .append("<p>PARAMS page - page number for pagination; size - size of page</p>")
                    .append("<p>/notify METOD GET  PARAMS page=1 size=5</p>")
                    .append("<p>/tariffs/update/{id} METOD POST PARAMS id REQUEST BODY json object tariff detais</p>");
             return  sb.toString();
        }
        @RequestMapping(value="/test/{id}", method=RequestMethod.GET)
        public ServiceResponse<?> test(@PathVariable(value="id") Long tariffId){
            TariffDTO t= tariffService.findById(tariffId);
             Random randomGenerator = new Random();
             
             float start=0.1f;
             float end=20.0f;
             float range =end-start;
    
            float fraction = (float)(0.9*range * randomGenerator.nextFloat());
            float f=start+fraction;
            BigDecimal value = new BigDecimal(f,new MathContext(2, RoundingMode.HALF_EVEN));
           
            t.getTariffDetailsCollection().forEach(d->d.setPricePerUnit(value ));
            t.getTariffDetailsCollection().forEach(d->{tariffService.updateDetailsFor(t.getId(), d);});        
                  
            
            return new ServiceResponse<>(HttpStatus.OK);
        }
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
        
	public ServiceResponse<Page<UserDTO>> listUsers(@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="5") int size) {
             if(size==0)throw new IllegalArgumentException("Page size must not be less than one!");
            if(size>100)size=100;
		PageRequest request = new PageRequest(page, size);
		Page<UserDTO> pageData = userService.findAll(request);
		
		
		for(UserDTO user:pageData.getContent()) {
			user.setTariffName(tariffService.findById(user.getTariffId()).getName());
		}
		return new ServiceResponse<>(pageData);
	}
	@RequestMapping(value="/tariffs", method=RequestMethod.GET)
	public ServiceResponse<Page<TariffDTO>> listTariffs(@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="5") int size) {
            if(size==0)throw new IllegalArgumentException("Page size must not be less than one!");
            if(size>100)size=100;
                             
            
		PageRequest request = new PageRequest(page, size);
		Page<TariffDTO> pageData = tariffService.findAll(request);
		
		
		return new ServiceResponse<>(pageData);
	}
        
	
        
	@RequestMapping(value="/notify/{nType}", method=RequestMethod.POST)
	public ServiceResponse<NotificationDTO> sendNotification(
                @PathVariable(value="nType") int notificationType,
                @RequestBody NotifyRequest request) {
		if(request.getUser().getId()==0||request.getTariff().getId()==0
                        ||notificationType<0||notificationType>1)
                    throw new IllegalArgumentException();
		NotificationDTO note=notificationService.processRequestNotification(notificationType,request);
		
		return new ServiceResponse<>(note);
	}

        
        @RequestMapping(value="/notify/empty", method=RequestMethod.GET)
	public ServiceResponse<NotifyRequest> getEmptyNotificationRequest() {
		
		
		return new ServiceResponse<>(NotifyRequest.getEmpty());
	}
        
        @RequestMapping(value="/tariffs/update/{id}", method=RequestMethod.POST)
	public ServiceResponse<?> ubdateTariffDetails( @PathVariable(value="id") Long tariffId,
                @RequestBody TariffDetailsDTO details) {
		tariffService.updateDetailsFor(tariffId,details);
		return new ServiceResponse<>(HttpStatus.OK);
	}
	
}
