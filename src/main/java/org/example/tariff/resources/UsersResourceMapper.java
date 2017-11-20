/**
 * 
 */
package org.example.tariff.resources;


import java.util.List;
import java.util.stream.Collectors;



import  org.example.tariff.entities.User;
import  org.example.tariff.model.UserDTO;
import  org.example.tariff.utils.BeanCopyUtil;


public class UsersResourceMapper extends AbstractResourceMapper<User, UserDTO> 
{
	
	
        @Override
        public UserDTO populateDTO(User content) {
            return BeanCopyUtil.toUserDTO(content);
        }
	@Override
	public List<UserDTO> populateDTO(List<User> content)
	{
            return
             content.stream()
                     .map(user->BeanCopyUtil.toUserDTO(user))
                     .collect(Collectors.toList());

	}

	
}
