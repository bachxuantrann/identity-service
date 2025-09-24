package com.spring.identity_service.service;

import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.entity.User;
import com.spring.identity_service.exception.AppException;
import com.spring.identity_service.exception.ErrorCode;
import com.spring.identity_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setActivationDate(request.getActivationDate());
        user.setExpirationDate(request.getExpirationDate());
        return this.userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    public User getUserById(String id){
        return this.userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("User with id "+id+" not found")
        );
    }
    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUserById(userId);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setActivationDate(request.getActivationDate());
        user.setExpirationDate(request.getExpirationDate());
        return this.userRepository.save(user);
    }
    public void deleteUser(String id){
        this.userRepository.deleteById(id);
    }
    
    public List<User> getUsersForCreateOrg() {
        return this.userRepository.getUsersForCreateOrg(LocalDate.now());
    }
}
