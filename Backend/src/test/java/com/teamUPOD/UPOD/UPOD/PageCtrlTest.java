package com.teamUPOD.UPOD.UPOD;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import datatypes.Page;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageCtrlTest {

    @Autowired
    private MockMvc mvc;
    
    @Mock
    private PageService pageService;

    private int testId = 12345;
    private Page testPage = new Page();
    
    @Test
    public void updateSuccessTest() throws Exception {
    		Mockito.when(pageService.updatePage(testId, testPage)).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders.post("/update/12345")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFailTest() throws Exception {
    		Mockito.when(pageService.updatePage(testId, testPage)).thenReturn(false);
        mvc.perform(MockMvcRequestBuilders.post("/update/12345")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void getTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/get/12345")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void deleteTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/delete/12345")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}