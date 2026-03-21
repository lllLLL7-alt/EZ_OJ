package test_Evaluation_machine.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import test_Evaluation_machine.Pojo.Problem;
import java.util.List;

@Mapper
public interface ProblemMapper {
    // 原有方法：根据题目编号查询题目
    @Select("SELECT * FROM problem_info WHERE problem_num = #{problemNum}")
    Problem getProblem(String problemNum);

    // 【新增】查询所有题目列表
    @Select("SELECT * FROM problem_info ORDER BY problem_num ASC")
    List<Problem> getAllProblems();
}