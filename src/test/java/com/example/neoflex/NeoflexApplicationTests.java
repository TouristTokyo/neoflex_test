package com.example.neoflex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NeoflexApplicationTests {


    private final MockMvc mockMvc;

    @Autowired
    NeoflexApplicationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;

    }

    @Test
    void test1() throws Exception {
        mockMvc.perform(get("/calculate?avg_salary=1000&days=28"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("28000"));
    }

    @Test
    void test2() throws Exception {
        mockMvc.perform(get("/calculate?days=28"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test3() throws Exception {
        mockMvc.perform(get("/calculate"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test4() throws Exception {
        mockMvc.perform(get("/calculate?avg_salary=10000"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test5() throws Exception {
        mockMvc.perform(get("/calculate?avg_salary=string&days=28"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test6() throws Exception {
        mockMvc.perform(get("/calculate?avg_salary=10000&days=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    void test7() throws Exception {
        mockMvc.perform(get("/calculate?avg_salary=-10&days=0"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test8() throws Exception {
        mockMvc.perform(get("/calculate?avg_salary=10000&days=-20"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test9() throws Exception {
        mockMvc.perform(get("/calculate?avg_salary=-10000&days=-20"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
