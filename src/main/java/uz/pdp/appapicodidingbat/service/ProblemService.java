package uz.pdp.appapicodidingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appapicodidingbat.entity.Problem;
import uz.pdp.appapicodidingbat.entity.Section;
import uz.pdp.appapicodidingbat.payload.ProblemDTO;
import uz.pdp.appapicodidingbat.payload.Result;
import uz.pdp.appapicodidingbat.repository.ProblemRepository;
import uz.pdp.appapicodidingbat.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    final ProblemRepository problemRepository;
    final SectionRepository sectionRepository;

    public ProblemService(ProblemRepository problemRepository, SectionRepository sectionRepository) {
        this.problemRepository = problemRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<Problem> getProblems() {
        return problemRepository.findAll();
    }

    public Problem getProblem(Integer problemId) {

        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        return optionalProblem.orElse(null);
    }

    public Result addProblem(ProblemDTO problemDTO) {

        Optional<Section> optionalSection = sectionRepository.findById(problemDTO.getSectionId());
        if (!optionalSection.isPresent())
            return new Result("Invalid Section Id!", false);

        Problem problem = new Problem();
        problem.setProblem(problemDTO.getProblem());
        problem.setBody(problemDTO.getBody());
        problem.setSection(optionalSection.get());
        problem.setTitle(problem.getTitle());
        problem.setSolution(problem.getSolution());
        problemRepository.save(problem);
        return new Result("New Problem saved.", true);
    }

    public Result editProblem(Integer problemId, ProblemDTO problemDTO) {

        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if (!optionalProblem.isPresent())
            return new Result("Invalid Problem Id!", true);

        Optional<Section> optionalSection = sectionRepository.findById(problemDTO.getSectionId());
        if (!optionalSection.isPresent())
            return new Result("Invalid Section Id!", false);

        Problem problem = new Problem();
        problem.setProblem(problemDTO.getProblem());
        problem.setBody(problemDTO.getBody());
        problem.setSection(optionalSection.get());
        problem.setTitle(problem.getTitle());
        problem.setSolution(problem.getSolution());
        problemRepository.save(problem);
        return new Result("New Problem saved.", true);

    }

    public Result deleteProblem(Integer problemId) {

        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if (!optionalProblem.isPresent())
            return new Result("Invalid problem Id!", false);
        problemRepository.deleteById(problemId);
        return new Result("Problem deleted.", true);

    }
}
