package com.example.junitrestapi;

import com.example.junitrestapi.api.BookAPI;
import com.example.junitrestapi.entity.BookEntity;
import com.example.junitrestapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookAPI bookAPI;

    BookEntity book1 = new BookEntity(1L, "Sach 1", " Noi dung1", 5);
    BookEntity book2 = new BookEntity(2L, "Sach 2", " Noi dung2", 4);
    BookEntity book3 = new BookEntity(3L, "Sach 3", " Noi dung3", 2);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookAPI).build();
    }

    @Test
    public void getAllRecords_success() throws Exception {
        List<BookEntity> records = new ArrayList<>(Arrays.asList(book1, book2, book3));

        Mockito.when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/book")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
//                        .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)));
//                        .andExpect(jsonPath("$[2].name", Matchers.is("book 3")));
    }

}
