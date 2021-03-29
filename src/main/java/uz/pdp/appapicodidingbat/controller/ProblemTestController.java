package uz.pdp.appapicodidingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodidingbat.entity.ProblemTest;
import uz.pdp.appapicodidingbat.payload.ProblemTestDTO;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.service.ProblemService;
import uz.pdp.appapicodidingbat.service.ProblemTestService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/problemTest")
public class ProblemTestController {

    final ProblemTestService problemTestService;

    public ProblemTestController(ProblemTestService problemTestService) {
        this.problemTestService = problemTestService;
    }

    @GetMapping
    public ResponseEntity<?> getProblemTests() {

        List<ProblemTest> problems = problemTestService.getProblems();
        return ResponseEntity.ok(problems);
    }

    @GetMapping(value = "/{problemTestId}")
    public ResponseEntity<?> getProblem(@PathVariable Integer problemTestId) {

        ProblemTest problem = problemTestService.getProblem(problemTestId);
        return ResponseEntity.ok(problem);
    }

    @PostMapping
    public ResponseEntity<?> addProblemTest(@Valid ProblemTestDTO problemTestDTO) {

        Result result = problemTestService.addProblemTest(problemTestDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @PutMapping(value = "/{problemTestId}")
    public ResponseEntity<?> editProblem(@PathVariable Integer problemTestId, @Valid ProblemTestDTO problemTestDTO) {

        Result result = problemTestService.editProblem(problemTestId, problemTestDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping(value = "/{problemTestId}")
    public ResponseEntity<?> deleteProblemTest(@PathVariable Integer problemTestId) {

        Result result = problemTestService.deleteProblemTest(problemTestId);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }
}
