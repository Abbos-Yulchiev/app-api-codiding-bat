package uz.pdp.appapicodidingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodidingbat.entity.Problem;
import uz.pdp.appapicodidingbat.payload.ProblemDTO;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.service.ProblemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/problem")
public class ProblemController {

    final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    public ResponseEntity<?> getProblems() {

        List<Problem> problems = problemService.getProblems();
        return ResponseEntity.ok(problems);
    }

    @GetMapping(value = "/{problemId}")
    public ResponseEntity<?> getProblem(@PathVariable Integer problemId) {

        Problem problem = problemService.getProblem(problemId);
        return ResponseEntity.ok(problem);
    }

    @PostMapping
    public ResponseEntity<?> addProblem(@Valid @RequestBody ProblemDTO problemDTO) {

        Result result = problemService.addProblem(problemDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @PutMapping(value = "/{problemId}")
    public ResponseEntity<?> editProblem(@PathVariable Integer problemId, @Valid @RequestBody ProblemDTO problemDTO) {

        Result result = problemService.editProblem(problemId, problemDTO);
        return ResponseEntity.status(result.getStatus() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping(value = "/{problemId}")
    public ResponseEntity<?> deleteProblem(@PathVariable Integer problemId) {

        Result result = problemService.deleteProblem(problemId);
        return ResponseEntity.status(result.getStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
}
