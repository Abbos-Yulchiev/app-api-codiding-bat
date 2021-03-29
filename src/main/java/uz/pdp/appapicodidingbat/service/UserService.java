package uz.pdp.appapicodidingbat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appapicodidingbat.entity.User;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.payload.UserDTO;
import uz.pdp.appapicodidingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }

    public User getUser(Integer userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public Result addUser(UserDTO userDTO) {

        boolean existsByEmail = userRepository.existsByEmail(userDTO.getEmail());
        if (existsByEmail)
            return new Result("Email already used, enter another one!", false);

        boolean existsByUserName = userRepository.existsByUserName(userDTO.getUserName());
        if (existsByUserName)
            return new Result("USer name already used, enter another userName!", false);

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setUserName(userDTO.getUserName());
        userRepository.save(user);
        return new Result("New User successfully saved.", true);
    }

    public Result editUser(Integer userId, UserDTO userDTO) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            return new Result("Invalid User id.", false);

        boolean existsByEmailAndIdNot = userRepository.existsByEmailAndIdNot(userDTO.getEmail(), userId);
        if (existsByEmailAndIdNot)
            return new Result("Email already used, enter another one!", false);

        boolean existsByUserNameAndIdNot = userRepository.existsByUserNameAndIdNot(userDTO.getUserName(), userId);
        if (existsByUserNameAndIdNot)
            return new Result("USer name already used, enter another userName!", false);

        User user = optionalUser.get();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setUserName(userDTO.getUserName());
        userRepository.save(user);
        return new Result("User edited", true);
    }

    public Result deleteUser(Integer userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            return new Result("Invalid user Id", false);

        userRepository.deleteById(userId);
        return new Result("User deleted.", true);
    }
}
