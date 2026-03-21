package test_Evaluation_machine.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import test_Evaluation_machine.Pojo.SubmitRecord;
import java.util.List;

@Mapper
public interface SubmitRecordMapper extends BaseMapper<SubmitRecord> {
    // 查询用户通过的所有题目编号（去重，AC状态）
    @Select("SELECT DISTINCT problem_num FROM submit_record WHERE user_id = #{userId} AND judge_status = 'AC'")
    List<String> getUserSolvedProblemNums(Integer userId);

    // 【新增】查询用户提交过的所有题目编号（去重，不管状态）
    @Select("SELECT DISTINCT problem_num FROM submit_record WHERE user_id = #{userId}")
    List<String> getUserSubmittedProblemNums(Integer userId);
}