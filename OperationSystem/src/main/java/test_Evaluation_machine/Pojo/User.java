package test_Evaluation_machine.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user_info") // 绑定数据库表
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username; // 用户名
    private String password; // 加密后的密码
    private String nickname; // 昵称
    private Date createTime; // 注册时间
    private Date updateTime; // 更新时间
}