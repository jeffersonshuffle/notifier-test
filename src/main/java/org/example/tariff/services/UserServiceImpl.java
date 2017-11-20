
package org.example.tariff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




import org.example.tariff.entities.User;
import org.example.tariff.model.UserDTO;
import org.example.tariff.repositories.UserRepository;
import org.example.tariff.resources.ResourceFactory;
import org.example.tariff.resources.UsersResourceMapper;


@Service
@Transactional
public class UserServiceImpl implements UserService 
{
	
	@Autowired UserRepository userRepository;
	@Transactional(
			readOnly= true ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
    @Override
	public Page<UserDTO> findAll(PageRequest page){
		
                ResourceFactory<User,UserDTO> f=new ResourceFactory<>(new UsersResourceMapper());
                
		return f.createResource( userRepository.findAll(page));
	}
	
	
	
}
