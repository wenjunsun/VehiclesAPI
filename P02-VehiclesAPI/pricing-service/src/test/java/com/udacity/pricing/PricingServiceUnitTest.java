package com.udacity.pricing;
import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
public class PricingServiceUnitTest {
    @Autowired
    private MockMvc mockMvc;

    // need to use @Mock here instead of @MockBean. Don't know why yet.
    @Mock
    PricingService pricingService;

    @Test
    public void getPriceUnitTest() throws Exception {
        mockMvc.perform(get("/services/price?vehicleId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{}")); // we expect to get a single JSON object back, not an array.

        verify(pricingService, times(1)).getPrice(1L);
    }
}
