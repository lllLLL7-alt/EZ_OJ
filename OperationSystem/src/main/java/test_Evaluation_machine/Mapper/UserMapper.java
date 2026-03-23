package test_Evaluation_machine.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import test_Evaluation_machine.Pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 继承BaseMapper，自动拥有增删改查能力
    // 额外补充：根据用户名查询用户（用于登录校验）
    @Select("SELECT * FROM user_info WHERE username = #{username}")
    User findByUsername(String username);
}