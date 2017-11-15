
package org.example.tariff.services;


import java.util.Date;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.example.tariff.entities.Tariff;
import org.example.tariff.entities.TariffDetails;

import org.example.tariff.exceptions.EntityNotFoundException;
import org.example.tariff.model.TariffDTO;
import org.example.tariff.model.TariffDetailsDTO;
import org.example.tariff.repositories.TariffDetailsRepository;

import org.example.tariff.repositories.TariffRepository;
import org.example.tariff.resources.ResourceFactory;
import org.example.tariff.resources.TariffResourceMapper;
import org.example.tariff.utils.BeanCopyUtil;



@Service
@Transactional
public class TariffService 
{
	
	@Autowired 
        TariffRepository tariffRepository;
        @Autowired  
        TariffDetailsRepository detailsRepository;
        
	@Transactional(
			readOnly= true ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
	public Page<TariffDTO> findAll(PageRequest page){
                ResourceFactory<Tariff,TariffDTO> f=new ResourceFactory<>(new TariffResourceMapper());
                
		return f.createResource( tariffRepository.findAll(page));
	}
        
        
        /**
         * Tariff find By Id
         * 
         * @param id Tariff
         * @return TariffDTO
         */
	@Transactional(
			readOnly= true ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
	public TariffDTO findById(Long id) {
		Tariff t= tariffRepository.findOne(id);
                if(t==null)
                 throw new EntityNotFoundException(Tariff.class,new String[] {"tariffId",id.toString()});
                return BeanCopyUtil.toTariffDTO(t);
	}
        
        @Transactional(
			readOnly= false ,
			timeout=30,
			propagation= Propagation. SUPPORTS ,
			isolation= Isolation. DEFAULT )
        public void updateDetails(TariffDetailsDTO details) {
            if(details==null)throw new IllegalArgumentException("details must not be null");
            Long tariffId=details.getTariffId();
            if(!tariffRepository.exists(tariffId))
                throw new EntityNotFoundException(Tariff.class,new String[] {"tariffId",tariffId.toString()});
            Long id=details.getId();
            TariffDetails item;
            try{
            item = detailsRepository.findById(id)
                    .orElseThrow(
                            
                            ()->{throw new EntityNotFoundException(TariffDetails.class,
                                    new String[] {"detailsId",id.toString()});}                                       
                    );
            }
            catch(Throwable ex){
                throw new EntityNotFoundException((EntityNotFoundException)ex);
            }
            TariffDetails d= BeanCopyUtil.toTariffDetails(details);
          
            item.updateFrom(d);
            detailsRepository.save(item);
            
            Tariff t= tariffRepository.findOne(item.getTariffId().getId());
            t.setDateCreated(new Date());
            tariffRepository.save(t);
        }
	
        
	
}
