package test_Evaluation_machine.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import test_Evaluation_machine.Mapper.UserMapper;
import test_Evaluation_machine.Pojo.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    // BCrypt加密器：用于密码加密和校验
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 用户注册
     * @param user 注册信息（username、password、nickname）
     * @return 注册成功的用户信息（不含密码）
     */
    public User register(User user) {
        // 1. 校验用户名是否已存在
        User existUser = userMapper.findByUsername(user.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        // 2. 密码加密（核心：禁止存明文）
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // 3. 插入数据库
        userMapper.insert(user);
        // 4. 返回用户信息（清空密码，避免泄露）
        user.setPassword(null);
        return user;
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 明文密码
     * @return 登录成功的用户信息（不含密码）
     */
    public User login(String username, String password) {
        // 1. 根据用户名查询用户
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 2. 校验密码（BCrypt自动比对明文和密文）
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 3. 返回用户信息（清空密码）
        user.setPassword(null);
        return user;
    }
    @Autowired
    private SubmitRecordService submitRecordService;

    // 获取用户通过的题目列表
    public List<String> getUserSolvedProblems(Integer userId) {
        return submitRecordService.getUserSolvedProblemNums(userId);
    }
}