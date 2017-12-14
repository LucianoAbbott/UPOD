package com.teamUPOD.UPOD.UPOD;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageCtrlTest {

    @Autowired
    private MockMvc mvc;
    
    @Mock
    PageService pageService;
    
    private int testId = 12345;
    
    @Before
    public void setUp() {
    }
    
    @Test
    @Ignore
    public void updateSuccessTest() throws Exception {
    		mvc.perform(MockMvcRequestBuilders.post("/update/" + testId)
        		.accept(MediaType.APPLICATION_JSON)
        		.content("{\"Page\": null}")
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void updateFailTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/update/" + testId)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @Ignore
    public void getTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/get/" + testId)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    @Ignore
    public void deleteTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/delete/" + testId)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}