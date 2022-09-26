package com.example.demo;

import static com.example.demo.ApiDocumentUtils.getDocumentRequest;
import static com.example.demo.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.domain.counter.Counter;
import com.example.demo.services.CounterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterDocumentationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CounterService counterService;

    @Test
    void getCounter() throws Exception {
        Counter data = new Counter();
        data.setUsername("test");

        this.mockMvc.perform(
                get("/api/counter/{username}", data.getUsername())
                    .accept(MediaType.APPLICATION_JSON)) // 1
            .andExpect(status().isOk())
            .andDo(document("get-counter",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("username").description("아이디")
                ),
                responseFields( // 2
                    fieldWithPath("counterNum").type(JsonFieldType.NUMBER)
                        .description("기록된 Counter 숫자")
                )
            ));
    }
}