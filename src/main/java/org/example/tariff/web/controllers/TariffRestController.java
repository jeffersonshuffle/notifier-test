package org.example.tariff.web.controllers;

 

import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import java.util.Random;
import javax.validation.Valid;
import org.example.tariff.exceptions.EntityNotFoundException;
import org.example.tariff.exceptions.EntityUpdateException;

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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;







@RestController
@RequestMapping(value="/api-tariff-notifier")
public class TariffRestController
{
	
	
	@Autowired
	private TariffService tariffService;
	@Autowired
	private UserService userService;
	@Autowired
	private NotificationService notificationService;
        
        @RequestMapping(value="/", method=RequestMethod.GET)
        public String home(){

             return  "hello";
        }
        
        @ApiOperation(value = "Testing update details of tariff with id")
        @RequestMapping(value="/test/{id}", method=RequestMethod.GET)
        public HttpEntity<?> test(@PathVariable(value="id") Long tariffId)
                throws EntityNotFoundException, EntityUpdateException
        {
            
            TariffDTO t= tariffService.findById(tariffId);
                   
             Random randomGenerator = new Random();
             
             float start=0.1f;
             float end=20.0f;
             float range =end-start;
    
            float fraction = (float)(0.9*range * randomGenerator.nextFloat());
            float f=start+fraction;
            BigDecimal value = new BigDecimal(f,new MathContext(2, RoundingMode.HALF_EVEN));
           
            t.getTariffDetailsCollection().forEach(d->d.setPricePerUnit(value ));
            t.getTariffDetailsCollection().forEach(d->{tariffService.updateDetails(d);});        
            
            
            return new ServiceResponse<>(HttpStatus.OK);
        }
        
	@ApiOperation(value ="View a list of available users")
	@RequestMapping(value="/users", method=RequestMethod.GET)
        
	public HttpEntity<Page<UserDTO>> listUsers(@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="1") int size) {
           
            if(size>100)size=100;
		PageRequest request = new PageRequest(page, size);
		Page<UserDTO> pageData = userService.findAll(request);
		
		
		pageData.getContent().stream().forEach(user->{
			user.setTariffName(tariffService.findById(user.getTariffId()).getName());}
		);
		return new ServiceResponse<>(pageData);
	}
        
        @ApiOperation(value ="View a list of available tariffs")
	@RequestMapping(value="/tariffs", method=RequestMethod.GET)
	public HttpEntity<Page<TariffDTO>> listTariffs(@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="1") int size) {
           
            if(size>100)size=100;
                             
            
		PageRequest request = new PageRequest(page, size);
		Page<TariffDTO> pageData = tariffService.findAll(request);
		
		
		return new ServiceResponse<>(pageData);
	}
        
	@PostMapping
        @ApiOperation(value ="Notify user about tariff changes")
	@RequestMapping(value="/notify/t", method=RequestMethod.POST)
	public HttpEntity<NotificationDTO> sendNotificationTemplate(
               @Valid
                @RequestBody NotifyRequest request) {
		
                
		NotificationDTO note=notificationService.processRequestNotification(
                        NotificationService.NotificationType.TEMPLATE,
                        request
                    );
		if(note.getSubject()!=null)
                    return new ServiceResponse<>(note);
                else
                    return new ServiceResponse<>(HttpStatus.OK);
	}
        @PostMapping
        @ApiOperation(value ="Notify user about tariff changes")
	@RequestMapping(value="/notify", method=RequestMethod.POST)
	public HttpEntity<NotificationDTO> sendNotification(
               
                @Valid
                @RequestBody NotifyRequest request) {
            
            
                
            NotificationDTO note=notificationService.processRequestNotification(
                    NotificationService.NotificationType.MESSAGE,
                    request
                );
            if(note.getSubject()!=null)
                return new ServiceResponse<>(note);
            else
                return new ServiceResponse<>(HttpStatus.OK);
	}

        @ApiOperation(value ="Get empty notification request for /notify" )
        @RequestMapping(value="/notify/empty", method=RequestMethod.GET)
	public HttpEntity<NotifyRequest> getEmptyNotificationRequest() {
		
		
		return new ServiceResponse<>(NotifyRequest.getEmpty());
	}
        
        @ApiOperation(value ="Update tariff details" )
        @PostMapping
        @RequestMapping(value="/tariffs/update", method=RequestMethod.PUT)
	public HttpEntity<?> ubdateTariffDetails( 
                @Valid
                @RequestBody TariffDetailsDTO details) {
		tariffService.updateDetails(details);
		return new ServiceResponse<>(HttpStatus.CREATED);
	}
	
}
