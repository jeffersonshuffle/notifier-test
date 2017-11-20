
package org.example.tariff;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

import java.util.List;


import org.example.tariff.model.TariffDTO;
import org.example.tariff.model.UserDTO;

import org.example.tariff.services.TariffService;
import org.example.tariff.services.UserService;
import org.example.tariff.web.controllers.TariffRestController;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.PageRequest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.tariff.model.ServiceResponse;
import org.example.tariff.services.NotificationService;
import static org.mockito.BDDMockito.given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(TariffRestController.class)
public class TariffControllerTest {
 private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private TariffService tariffService;
    @MockBean
    private UserService userService;
    @MockBean
    private NotificationService notificationService;
 
    
    private JacksonTester<Page<TariffDTO>> jsonTariffDto;
    private JacksonTester<Page<UserDTO>> jsonUserDto;
    
    
    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }
    
    @Test
    public void canRetrieveAllTariffsWhenExists() throws Exception {
        // given
        PageRequest page=new PageRequest(0,5);
        Page<TariffDTO> tariffs=getAllTariff( page);
        given(tariffService.findAll(page)).willReturn(tariffs);
                
       
        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api-tariff-notifier/tariffs?page=0&size=5")
                        .accept(MediaType.APPLICATION_JSON))
                
                .andReturn().getResponse();
        
        // then
        
        logger.info("!!!!!!!!!!!!!!!   "+response.getContentAsString());
        ServiceResponse<Page<TariffDTO>> resp=new ServiceResponse<>(tariffs);
        logger.info("!!!!!!!!!!!!!!!   "+jsonTariffDto.write(resp.getBody()).getJson());
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        
        assertThat(response.getContentAsString().length()).isEqualTo(
                jsonTariffDto.write(resp.getBody()).getJson().length()
        );
    }
    
    Page<TariffDTO> getAllTariff(PageRequest page){
        List<TariffDTO> ts= Arrays.asList(
                new TariffDTO(){{setId(1L);setName("t1");}},
                new TariffDTO(){{setId(2L);setName("t2");}},
               new TariffDTO(){{setId(3L);setName("t3");}},
                new TariffDTO(){{setId(4L);setName("t4");}},
                 new TariffDTO(){{setId(5L);setName("t5");}}
        );  
        PageImpl data=new PageImpl(ts);
        return data;
    }
        @Test
    public void canRetrieveAllUsersWhenExists() throws Exception {
        // given
        PageRequest page=new PageRequest(0,5);
        Page<UserDTO> users=getAllUser( page);
        given(userService.findAll(page))
                .willReturn(users);
        users.getContent().stream().forEach(user->{
            given(tariffService.findById(user.getTariffId()))
                    .willReturn(new TariffDTO(){{setName("t");}});
        });
        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api-tariff-notifier/users?page=0&size=5")
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();
        
        // then
        
        logger.info("!!!!!!!!!!!!!!!   "+response.getContentAsString());
        ServiceResponse<Page<UserDTO>> resp=new ServiceResponse<>(users);
        logger.info("!!!!!!!!!!!!!!!   "+jsonUserDto.write(resp.getBody()).getJson());
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        
        assertThat(response.getContentAsString().length()).isEqualTo(
                jsonUserDto.write(resp.getBody()).getJson().length()
        );
    }
    
    Page<UserDTO> getAllUser(PageRequest page){
        List<UserDTO> ts= Arrays.asList(
                new UserDTO(){{setId(1L);setFirstName("t1");}},
                new UserDTO(){{setId(2L);setFirstName("t2");}},
               new UserDTO(){{setId(3L);setFirstName("t3");}},
                new UserDTO(){{setId(4L);setFirstName("t4");}},
                 new UserDTO(){{setId(5L);setFirstName("t5");}}
        );  
        PageImpl data=new PageImpl(ts);
        return data;
    }
    
  

  
}

