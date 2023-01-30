package com.spring.msuser.dataprovider.userdatabase;

import com.spring.msuser.dataprovider.userdatabase.entities.UserEntity;
import com.spring.msuser.dataprovider.userdatabase.repository.UserRepository;
import com.spring.msuser.dataprovider.userdatabase.translator.UserTranslator;
import com.spring.msuser.domain.dataprovider.userdatabase.UserDataProvider;
import com.spring.msuser.domain.model.User;
import com.spring.msuser.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDataProviderImpl implements UserDataProvider {

    private final UserRepository userRepository;
    private final UserTranslator userTranslator;


    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        log.info("c=UserDataProviderImpl, m=findById, id={}, userEntity={}", id, userEntity);
        return userTranslator.execute(userEntity);

    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        log.info("c=UserDataProviderImpl, m=findByEmail, email={}, userEntity={}", email, userEntity);
        return userTranslator.execute(userEntity);
    }
}
