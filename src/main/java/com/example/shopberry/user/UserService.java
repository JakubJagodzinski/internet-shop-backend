package com.example.shopberry.user;

import com.example.shopberry.user.dto.ChangePasswordRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private static final String WRONG_PASSWORD_MESSAGE = "Wrong password";
    private static final String PASSWORDS_ARE_NOT_THE_SAME_MESSAGE = "Passwords are not the same";

    @Transactional
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto, Principal connectedUser) throws IllegalArgumentException {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException(WRONG_PASSWORD_MESSAGE);
        }

        if (!changePasswordRequestDto.getNewPassword().equals(changePasswordRequestDto.getConfirmationPassword())) {
            throw new IllegalArgumentException(PASSWORDS_ARE_NOT_THE_SAME_MESSAGE);
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));

        userRepository.save(user);
    }

}
