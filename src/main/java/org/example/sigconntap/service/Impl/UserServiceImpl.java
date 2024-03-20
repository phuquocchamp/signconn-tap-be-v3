package org.example.sigconntap.service.Impl;

import lombok.AllArgsConstructor;
import org.example.sigconntap.dto.UserDto;
import org.example.sigconntap.entity.User;
import org.example.sigconntap.exception.EmailAlreadyExitsException;
import org.example.sigconntap.repository.UserRepository;
import org.example.sigconntap.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> retrieveUser = userRepository.findByEmail(userDto.getEmail());
        if (retrieveUser.isPresent()){
            throw new EmailAlreadyExitsException("Email Already Exits");
        }else{
            User savedUser = userRepository.save(modelMapper.map(userDto, User.class));
            return modelMapper.map(savedUser, UserDto.class);
        }
    }
}
