package test_Evaluation_machine.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test_Evaluation_machine.Pojo.User;
import test_Evaluation_machine.Service.SubmitRecordService;
import test_Evaluation_machine.Service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SubmitRecordService submitRecordService;

    /**
     * 用户注册接口
     * POST /user/register
     */
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        log.info("用户注册：{}", user.getUsername());
        return userService.register(user);
    }

    /**
     * 用户登录接口
     * POST /user/login
     */
    @PostMapping("/login")
    public User login(@RequestBody User loginUser, HttpSession session) {
        log.info("用户登录：{}", loginUser.getUsername());
        User user = userService.login(loginUser.getUsername(), loginUser.getPassword());
        // 登录成功：将用户信息存入Session（后续鉴权用）
        session.setAttribute("currentUser", user);
        return user;
    }

    /**
     * 获取当前登录用户信息（用于前端展示）
     * GET /user/current
     */
    @GetMapping("/current")
    public User getCurrentUser(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new RuntimeException("未登录");
        }
        return currentUser;
    }

    /**
     * 用户退出登录
     * POST /user/logout
     */
    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate(); // 销毁Session
    }

    // 【修改】获取当前登录用户通过的题目列表
    @GetMapping("/solved")
    public List<String> getUserSolvedProblems(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new RuntimeException("未登录");
        }
        log.info("获取用户{}通过的题目", currentUser.getUsername());
        return userService.getUserSolvedProblems(currentUser.getId());
    }
    // 【新增】获取当前登录用户的题目状态（已通过、已提交）
    @GetMapping("/problem-status")
    public Map<String, Object> getUserProblemStatus(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new RuntimeException("未登录");
        }
        log.info("获取用户{}的题目状态", currentUser.getUsername());
        // 查询已通过、已提交的题目
        List<String> solved = submitRecordService.getUserSolvedProblemNums(currentUser.getId());
        List<String> submitted = submitRecordService.getUserSubmittedProblemNums(currentUser.getId());

        // 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("solved", solved);
        result.put("submitted", submitted);
        return result;
    }
}