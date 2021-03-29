package uz.pdp.appapicodidingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appapicodidingbat.entity.Problem;
import uz.pdp.appapicodidingbat.entity.ProblemTest;
import uz.pdp.appapicodidingbat.payload.ProblemTestDTO;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.repository.ProblemRepository;
import uz.pdp.appapicodidingbat.repository.ProblemTestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTestService {

    final ProblemTestRepository problemTestRepository;
    final ProblemRepository problemRepository;

    public ProblemTestService(ProblemTestRepository problemTestRepository, ProblemRepository problemRepository) {
        this.problemTestRepository = problemTestRepository;
        this.problemRepository = problemRepository;
    }


    public List<ProblemTest> getProblems() {
        return problemTestRepository.findAll();
    }

    public ProblemTest getProblem(Integer problemTestId) {

        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(problemTestId);
        return optionalProblemTest.orElse(null);

    }

    public Result addProblemTest(ProblemTestDTO problemTestDTO) {

        Optional<Problem> optionalProblem = problemRepository.findById(problemTestDTO.getProblemId());

        if (!optionalProblem.isPresent())
            return new Result("Invalid Problem Id!", false);

        ProblemTest problemTest = new ProblemTest();
        problemTest.setProblem(optionalProblem.get());
        problemTest.setArguments(problemTestDTO.getArguments());
        problemTest.setResult(problemTestDTO.getResult());
        problemTestRepository.save(problemTest);
        return new Result("Problem Test added.", true);
    }

    public Result editProblem(Integer problemTestId, ProblemTestDTO problemTestDTO) {

        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(problemTestId);
        if (!optionalProblemTest.isPresent())
            return new Result("Invalid Problem Test Id!", false);

        Optional<Problem> optionalProblem = problemRepository.findById(problemTestDTO.getProblemId());
        if (!optionalProblem.isPresent())
            return new Result("Invalid Problem Id!", false);

        ProblemTest problemTest = optionalProblemTest.get();
        problemTest.setProblem(optionalProblem.get());
        problemTest.setArguments(problemTestDTO.getArguments());
        problemTest.setResult(problemTestDTO.getResult());
        problemTestRepository.save(problemTest);
        return new Result("Problem Test added.", true);
    }

    public Result deleteProblemTest(Integer problemTestId) {

        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(problemTestId);
        if (!optionalProblemTest.isPresent())
            return new Result("Invalid Problem Test Id!", false);

        problemTestRepository.deleteById(problemTestId);
        return new Result("Problem Test deleted.", true);
    }
}
