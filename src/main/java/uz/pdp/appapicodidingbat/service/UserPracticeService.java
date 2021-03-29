package uz.pdp.appapicodidingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appapicodidingbat.entity.Problem;
import uz.pdp.appapicodidingbat.entity.User;
import uz.pdp.appapicodidingbat.entity.UserPractice;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.payload.UserPracticeDTO;
import uz.pdp.appapicodidingbat.repository.ProblemRepository;
import uz.pdp.appapicodidingbat.repository.UserPracticeRepository;
import uz.pdp.appapicodidingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserPracticeService {

    final UserPracticeRepository userPracticeRepository;
    final ProblemRepository problemRepository;
    final UserRepository userRepository;

    public UserPracticeService(UserPracticeRepository userPracticeRepository, ProblemRepository problemRepository, UserRepository userRepository) {
        this.userPracticeRepository = userPracticeRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    public List<UserPractice> getUserPractises() {

        return userPracticeRepository.findAll();
    }

    public UserPractice getUserPractise(Integer id) {

        Optional<UserPractice> optionalUserPractice = userPracticeRepository.findById(id);
        return optionalUserPractice.orElse(null);
    }

    public Result addUserPractices(UserPracticeDTO userPracticeDTO) {

        Optional<User> optionalUser = userRepository.findById(userPracticeDTO.getUserId());
        if (!optionalUser.isPresent())
            return new Result("Invalid user Id!", false);

        Optional<Problem> optionalProblem = problemRepository.findById(userPracticeDTO.getProblemId());
        if (!optionalProblem.isPresent())
            return new Result("Invalid problem Id!", false);

        UserPractice userPractice1 = new UserPractice();
        userPractice1.setUserSolution(userPracticeDTO.getUserSolution());
        userPractice1.setScore(userPracticeDTO.getScore());
        userPractice1.setDate(userPracticeDTO.getDate());
        userPractice1.setUser(optionalUser.get());
        userPractice1.setProblem(optionalProblem.get());
        userPracticeRepository.save(userPractice1);
        return new Result("New User Practices saved!", true);
    }


    public Result editUserPractices(Integer id, UserPracticeDTO userPracticeDTO) {

        Optional<UserPractice> optionalUserPractice = userPracticeRepository.findById(id);
        if (!optionalUserPractice.isPresent())
            return new Result("Invalid User Practices Id!", false);

        Optional<User> optionalUser = userRepository.findById(userPracticeDTO.getUserId());
        if (!optionalUser.isPresent())
            return new Result("Invalid user Id!", false);

        Optional<Problem> optionalProblem = problemRepository.findById(userPracticeDTO.getProblemId());
        if (!optionalProblem.isPresent())
            return new Result("Invalid problem Id!", false);

        UserPractice userPractice = optionalUserPractice.get();
        userPractice.setUserSolution(userPracticeDTO.getUserSolution());
        userPractice.setScore(userPracticeDTO.getScore());
        userPractice.setDate(userPracticeDTO.getDate());
        userPractice.setUser(optionalUser.get());
        userPractice.setProblem(optionalProblem.get());
        userPracticeRepository.save(userPractice);
        return new Result("USer Practices Edited", true);
    }

    public Result deleteUserPractices(Integer id) {

        Optional<UserPractice> optionalUserPractice = userPracticeRepository.findById(id);
        if (!optionalUserPractice.isPresent())
            return new Result("Invalid User Practices Id!", false);

        userPracticeRepository.deleteById(id);
        return new Result("User Practices deleted!", true);
    }
}
