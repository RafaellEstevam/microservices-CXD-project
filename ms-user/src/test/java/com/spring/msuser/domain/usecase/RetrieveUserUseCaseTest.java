package com.spring.msuser.domain.usecase;

import com.spring.msuser.domain.dataprovider.userdatabase.UserDataProvider;
import com.spring.msuser.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RetrieveUserUseCaseTest {

    public static final String NAME = "Larissa Bulhões";
    public static final String EMAIL = "larissa.bulhoes@mock.com";
    public static final long ID = 1L;

    @Mock
    private UserDataProvider userDataProvider;

    @InjectMocks
    private RetrieveUserUseCase retrieveUserUseCase;


    @Test
    void shouldfindUserById() {

        User user = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

        Mockito.when(userDataProvider.findById(ArgumentMatchers.anyLong())).thenReturn(user);
        User retrievedUser = retrieveUserUseCase.findById(ArgumentMatchers.anyLong());

        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());

    }

    @Test
    void findByEmail() {

        User user = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

        Mockito.when(userDataProvider.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
        User retrievedUser = retrieveUserUseCase.findByEmail(ArgumentMatchers.anyString());

        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
    }
}