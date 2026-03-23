package test_Evaluation_machine.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test_Evaluation_machine.Pojo.Problem;
import test_Evaluation_machine.Service.ProblemService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    // 获取所有题目列表
    @GetMapping("/list")
    public List<Problem> getProblemList() {
        log.info("获取题目列表");
        return problemService.getAllProblems();
    }

    // 根据题目编号获取题目详情
    @GetMapping("/detail/{problemNum}")
    public Problem getProblemDetail(@PathVariable("problemNum") String problemNum) {
        log.info("获取题目详情：{}", problemNum);
        Problem problem = problemService.getProblemByNum(problemNum);
        if (problem == null) {
            throw new RuntimeException("题目不存在");
        }
        return problem;
    }
}