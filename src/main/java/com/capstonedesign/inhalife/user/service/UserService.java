package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.exception.DuplicatedEmailException;
import com.capstonedesign.inhalife.user.exception.DuplicatedNicknameException;
import com.capstonedesign.inhalife.user.exception.DuplicatedUserException;
import com.capstonedesign.inhalife.user.exception.NotExistedUserException;
import com.capstonedesign.inhalife.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long signUp(User user) {
        Optional<User> duplicatedEmail = userRepository.findByEmail(user.getEmail());
        if(duplicatedEmail.isPresent()) throw new DuplicatedEmailException();

        Optional<User> duplicatedNickname = userRepository.findByNickname(user.getNickname());
        if(duplicatedNickname.isPresent()) throw new DuplicatedNicknameException();

        return userRepository.save(user);
    }

    public User getById(Long id) {
        if(id == null) throw new NotExistedUserException();

        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new NotExistedUserException();

        return user.get();
    }

    public User login(String email, String password) {
        Optional<User> user = userRepository.login(email, password);
        if(!user.isPresent()) throw new NotExistedUserException();

        return user.get();
    }

    @Transactional
    public void deleteUser(Long id) {
        if(id == null) throw new NotExistedUserException();

        userRepository.delete(id);
    }
}
