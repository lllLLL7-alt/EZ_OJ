package test_Evaluation_machine.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_Evaluation_machine.Mapper.ProblemMapper;
import test_Evaluation_machine.Pojo.Problem;
import java.util.List;

@Service
public class ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    // 获取所有题目列表
    public List<Problem> getAllProblems() {
        return problemMapper.getAllProblems();
    }

    // 根据题目编号获取题目详情
    public Problem getProblemByNum(String problemNum) {
        return problemMapper.getProblem(problemNum);
    }
}