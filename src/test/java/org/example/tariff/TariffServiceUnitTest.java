

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.example.tariff.entities.Tariff;
import org.example.tariff.entities.TariffDetails;
import org.example.tariff.exceptions.EntityNotFoundException;
import org.example.tariff.exceptions.EntityUpdateException;
import org.example.tariff.model.TariffDTO;
import org.example.tariff.model.TariffDetailsDTO;
import org.example.tariff.repositories.TariffDetailsRepository;
import org.example.tariff.repositories.TariffRepository;
import org.example.tariff.services.TariffService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TariffServiceUnitTest {
 
    @TestConfiguration
    static class TariffServiceUnitTestContextConfiguration {
  
        @Bean
        public TariffService tariffService() {
            return new TariffService();
        }
    }
 
    @Autowired
    private TariffService tariffService;
 
    @MockBean
    private TariffRepository tariffRepository;
    @MockBean
    private TariffDetailsRepository detailsRepository;
 
    @Test
    public void canRetreiveTariffWithIdWhenExists() {
        Tariff tariff = new Tariff();
        tariff.setId(1L);
        Mockito.when(tariffRepository.findOne(tariff.getId()))
                .thenReturn(tariff);
        
        TariffDTO found= tariffService.findById(1L);
        
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1L);
      
        
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void canRetrieveTariffByIdWhenDoesNotExist()  {
      
        EntityNotFoundException failure=
            new EntityNotFoundException(Tariff.class,new String[] {"id","0"});
        Mockito.when(tariffRepository.findOne(0L))
                .thenThrow(failure);

        
       

        tariffService.findById(0L);
    }
    
     @Test
    public void canRetreiveAllWhenExists() {
        //given
        PageRequest pgbl = new PageRequest(0, 5); 
        Page<Tariff> data= getAllTariff(pgbl);
        Mockito.when(tariffRepository.findAll(pgbl))
                .thenReturn(data);
        //when
       Page<TariffDTO> found= tariffService.findAll(pgbl);
       
       //than
       
        assertThat(found).isNotNull();
        assertThat(found).isNotEmpty();
        assertThat(found.getTotalElements()).isEqualTo(5);
        assertThat(found.getTotalPages()).isEqualTo(1);
        assertThat(found.iterator().next()).isExactlyInstanceOf(TariffDTO.class);
        assertThat(found.iterator().next().getId()).isBetween(1L, 5L);
    }
    
    Page<Tariff> getAllTariff(PageRequest page){
        List<Tariff> ts= Arrays.asList(
                new Tariff(){{setId(1L);setName("t1");}},
                new Tariff(){{setId(2L);setName("t2");}},
               new Tariff(){{setId(3L);setName("t3");}},
                new Tariff(){{setId(4L);setName("t4");}},
                 new Tariff(){{setId(5L);setName("t5");}}
        );  
        PageImpl data=new PageImpl(ts);
        return data;
    }
  
     @Test(expected = EntityUpdateException.class)
    public void failUpdateTariffDetails()  {
       //given
        Optional<TariffDetails> opt=Optional.of(new TariffDetails(){{setId(0L);}});
         Mockito.when(detailsRepository.findById(opt.get().getId()))
                .thenReturn(opt);
          Mockito.when(tariffRepository.exists(Long.MAX_VALUE))
                .thenReturn(false);
        //when
          TariffDetailsDTO details=new TariffDetailsDTO(){{setId(1L);setTariffId(Long.MAX_VALUE);}};
        tariffService.updateDetails(details);
    }
}