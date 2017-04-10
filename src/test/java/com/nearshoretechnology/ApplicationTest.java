package com.nearshoretechnology;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request
  .MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result
  .MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet
  .AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void index() throws Exception {
    mockMvc.perform(get("/index.html"))
      .andExpect(content().string(containsString("Click")));
  }

  @Test
  public void hello() throws Exception {
    mockMvc.perform(get("/hello"))
      .andExpect(content().string(containsString("Hello World")));
  }

  @Test
  public void helloWithName() throws Exception {
    mockMvc.perform(get("/hello").param("name", "Romel"))
      .andExpect(content().string(containsString("Hello Romel")));
  }

}
