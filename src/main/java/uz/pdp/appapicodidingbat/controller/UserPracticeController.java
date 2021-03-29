package uz.pdp.appapicodidingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodidingbat.entity.UserPractice;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.payload.UserPracticeDTO;
import uz.pdp.appapicodidingbat.service.UserPracticeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/userPractice")
public class UserPracticeController {

    final UserPracticeService userPracticeService;

    public UserPracticeController(UserPracticeService userPracticeService) {
        this.userPracticeService = userPracticeService;
    }

    @GetMapping
    public ResponseEntity<?> getUserPractises() {

        List<UserPractice> userPractises = userPracticeService.getUserPractises();
        return ResponseEntity.ok(userPractises);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserPractise(@PathVariable Integer id) {

        UserPractice userPractise = userPracticeService.getUserPractise(id);
        return ResponseEntity.ok(userPractise);
    }

    @PostMapping
    public ResponseEntity<?> addUserPractices(@Valid UserPracticeDTO userPracticeDTO) {

        Result result = userPracticeService.addUserPractices(userPracticeDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> editUserPractices(@PathVariable Integer id, @Valid @RequestBody UserPracticeDTO userPracticeDTO) {

        Result result = userPracticeService.editUserPractices(id, userPracticeDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUserPractices(@PathVariable Integer id) {

        Result result = userPracticeService.deleteUserPractices(id);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }
}
