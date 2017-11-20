package org.example.tariff;


import static org.assertj.core.api.Assertions.assertThat;
import org.example.tariff.web.controllers.TariffRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TariffNotifierApplicationTests {
    @Autowired
    private TariffRestController controller;



	@Test
	public void contextLoads() {
            assertThat(controller).isNotNull();
	}

}
