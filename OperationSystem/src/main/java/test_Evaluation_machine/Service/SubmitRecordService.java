package test_Evaluation_machine.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import test_Evaluation_machine.Mapper.SubmitRecordMapper;
import test_Evaluation_machine.Pojo.SubmitRecord;
import java.util.List;

@Service
public class SubmitRecordService extends ServiceImpl<SubmitRecordMapper, SubmitRecord> {
    // 保存提交记录到数据库
    public void saveSubmitRecord(SubmitRecord record) {
        this.save(record);
    }

    // 获取用户通过的题目编号列表
    public List<String> getUserSolvedProblemNums(Integer userId) {
        return this.baseMapper.getUserSolvedProblemNums(userId);
    }

    // 【新增】获取用户提交过的所有题目编号
    public List<String> getUserSubmittedProblemNums(Integer userId) {
        return this.baseMapper.getUserSubmittedProblemNums(userId);
    }
}