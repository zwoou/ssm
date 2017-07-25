package bid.woou.ssm.web;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cc on 2017/7/5.
 */
@Controller
public class AdminController {
    // 登录成功的页面
    @RequestMapping(value = "/views/home")
    public String adminHomePage(){
        return "views/home";
    }

    // 只有角色为admin的才能访问
    @RequiresRoles("admin")
    @RequestMapping(value = "/views/home")
    public String adminWithRole(){
        return "views/withrole";
    }

    // 只用同时具有user:view和user:create权限才能访问
    @RequiresPermissions(value={"user:view","user:create"}, logical= Logical.AND)
    @RequestMapping(value = "/views/auth")
    public String adminWithAuth(){
        return "views/withauth";
    }
}
