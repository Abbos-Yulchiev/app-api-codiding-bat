package uz.pdp.appapicodidingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodidingbat.entity.User;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.payload.UserDTO;
import uz.pdp.appapicodidingbat.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam Integer page, @RequestParam Integer size) {

        List<User> users = userService.getUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId) {

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {

        Result result = userService.addUser(userDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Integer userId, @Valid @RequestBody UserDTO userDTO) {

        Result result = userService.editUser(userId, userDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {

        Result result = userService.deleteUser(userId);
        return ResponseEntity.status(result.getStatus() ? 202 : 409).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
