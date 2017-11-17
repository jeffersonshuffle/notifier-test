

import static org.assertj.core.api.Assertions.assertThat;
import org.example.tariff.entities.Tariff;
import org.example.tariff.model.TariffDTO;
import org.example.tariff.repositories.TariffDetailsRepository;
import org.example.tariff.repositories.TariffRepository;
import org.example.tariff.services.TariffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
    public void retreiveTariffWithIdExists() {
        Tariff tariff = new Tariff();
        tariff.setId(1L);
        Mockito.when(tariffRepository.findOne(tariff.getId()))
                .thenReturn(tariff);
        
        TariffDTO found= tariffService.findById(1L);
        
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1L);
      
        
    }
}