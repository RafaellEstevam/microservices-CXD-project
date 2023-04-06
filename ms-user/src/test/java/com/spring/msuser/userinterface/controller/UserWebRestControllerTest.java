package com.spring.msuser.userinterface.controller;

import com.spring.msuser.domain.model.User;
import com.spring.msuser.domain.usecase.RetrieveUserUseCase;
import com.spring.msuser.exception.UserNotFoundException;
import com.spring.msuser.userinterface.translator.UserResponseTranslator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = {UserWebRestController.class, UserResponseTranslator.class})
class UserWebRestControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "Johan Szewczuk";
    public static final String EMAIL = "johan.szewczuk@mock.com";
    public static final String JSON_CONTENT = "{\"id\": 1, \"name\":\"Johan Szewczuk\", \"email\":\"johan.szewczuk@mock.com\"}";
    public static final User USER = User.builder().id(ID).name(NAME).email(EMAIL).build();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetrieveUserUseCase retrieveUserUseCase;

    @Test
    public void whenFindByIdEndpointIsCalledThenShouldRetrieveUser() throws Exception {

        Mockito.when(retrieveUserUseCase.findById(ArgumentMatchers.eq(ID))).thenReturn(USER);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/user/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_CONTENT));
    }


    @Test
    public void whenUserIsNotFoundByIdThenShouldThrowAnExceptionAndReturnUserNotFound() throws Exception {

        Mockito.when(retrieveUserUseCase.findById(ArgumentMatchers.eq(ID))).thenThrow(UserNotFoundException.class);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void whenFindByEmailEndpointIsCalledThenShouldRetrieveUser() throws Exception {

        Mockito.when(retrieveUserUseCase.findByEmail(ArgumentMatchers.eq(EMAIL))).thenReturn(USER);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/user?email="+EMAIL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_CONTENT));
    }

    @Test
    public void whenUserIsNotFoundByEmailThenShouldThrowAnExceptionAndReturnUserNotFound() throws Exception {

        Mockito.when(retrieveUserUseCase.findByEmail(ArgumentMatchers.eq(EMAIL))).thenThrow(UserNotFoundException.class);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/user?email="+EMAIL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}














