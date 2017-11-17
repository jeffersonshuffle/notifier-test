package org.example.tariff.utils;

import org.example.tariff.entities.PriceChangeReport;
import org.example.tariff.entities.Tariff;
import org.example.tariff.entities.TariffDetails;
import org.example.tariff.entities.User;
import org.example.tariff.model.NotificationDTO;
import org.example.tariff.model.TariffDTO;
import org.example.tariff.model.TariffDetailsDTO;
import org.example.tariff.model.UserDTO;

import org.modelmapper.ModelMapper;


public final class BeanCopyUtil
{
	
	private BeanCopyUtil()
	{
	}
	public static UserDTO toUserDTO(User user)
	{
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		
		
		return userDTO;		
	}
	public static User toUser( UserDTO userDTO)
	{
		ModelMapper mapper = new ModelMapper();
		User  user= mapper.map(userDTO, User.class);
		return user;		
	}
	public static TariffDTO toTariffDTO( Tariff tariff)
	{
		ModelMapper mapper = new ModelMapper();
		 TariffDTO dto = mapper.map(tariff,  TariffDTO.class);
		return dto;		
	}
	public static  Tariff toTariff(  TariffDTO tDTO)
	{
		ModelMapper mapper = new ModelMapper();
		 Tariff  tariff= mapper.map(tDTO,  Tariff.class);
		return tariff;		
	}
        public static TariffDetailsDTO toTariffDetailsDTO( TariffDetails details)
	{
		ModelMapper mapper = new ModelMapper();
		 TariffDetailsDTO dto = mapper.map(details,  TariffDetailsDTO.class);
		return dto;		
	}
	public static  TariffDetails toTariffDetails(  TariffDetailsDTO tDTO)
	{
		ModelMapper mapper = new ModelMapper();
		 TariffDetails  details= mapper.map(tDTO,  TariffDetails.class);
		return details;		
	}
	public static NotificationDTO toNotificationDTO(PriceChangeReport content) {
		ModelMapper mapper = new ModelMapper();
		 
		return mapper.map(content,  NotificationDTO.class);	
	}
	
}
