import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by cc on 2017/7/4.
 */
public class JdbcRealmTest {
    /**
     * Shiro test.
     */
    @Test
    public void shiroTest() {
        // 1.获取SecurityManager工厂，此处使用ini配置文件初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        // 2.获取SecurityManager实例，并绑定到SecurityUtils
        SecurityManager sm = factory.getInstance();

        SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) sm);

        // 3.得到Subject
        Subject subject = SecurityUtils.getSubject();
        // 4.创建用户登录凭证
        UsernamePasswordToken token = new UsernamePasswordToken("1", "1");
        // 5.登录，如果登录失败会抛出不同的异常，根据异常输出失败原因
        try {
            subject.login(token);
            // 6.判断是否成功登录
            assertEquals(true, subject.isAuthenticated());
            System.out.println("登录成功！！");
            // 7.注销用户
            subject.logout();
        } catch (IncorrectCredentialsException e) {
            System.out.println("登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.");
        } catch (ExcessiveAttemptsException e) {
            System.out.println("登录失败次数过多");
        } catch (LockedAccountException e) {
            System.out.println("帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.");
        } catch (DisabledAccountException e) {
            System.out.println("帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.");
        } catch (ExpiredCredentialsException e) {
            System.out.println("帐号已过期. the account for username " + token.getPrincipal() + "  was expired.");
        } catch (UnknownAccountException e) {
            System.out.println("帐号不存在. There is no user with username of " + token.getPrincipal());
        }
    }

    /**
     * 用户授权
     */
    @Test
    public void test() {
        // 1.获取SecurityManager工厂，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        // 2.获取SecurityManager实例，并绑定到SecurityUtils
        SecurityManager sm = factory.getInstance();
        SecurityUtils.setSecurityManager(sm);
        // 3.得到Subject
        Subject subject = SecurityUtils.getSubject();
        // 4.创建用户登录凭证
        UsernamePasswordToken token = new UsernamePasswordToken("1", "1");
        token.setRememberMe(true);
        // 5.登录，如果登录失败会抛出不同的异常，根据异常输出失败原因
        try {
            subject.login(token);
            // 6.判断是否成功登录
            assertEquals(true, subject.isAuthenticated());
            System.out.println("登录成功！！");
            // 判断用户是否拥有某个角色
            assertEquals(true, subject.hasRole("超级管理员"));
            // 使用Shiro自带的断言判断用户是否有被授权
            subject.checkRole("manager");
            subject.checkPermission("create_user1");
            // 7.注销用户
            subject.logout();
        } catch (IncorrectCredentialsException e) {
            System.out.println("登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.");
        } catch (ExcessiveAttemptsException e) {
            System.out.println("登录失败次数过多");
        } catch (LockedAccountException e) {
            System.out.println("帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.");
        } catch (DisabledAccountException e) {
            System.out.println("帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.");
        } catch (ExpiredCredentialsException e) {
            System.out.println("帐号已过期. the account for username " + token.getPrincipal() + "  was expired.");
        } catch (UnknownAccountException e) {
            System.out.println("帐号不存在. There is no user with username of " + token.getPrincipal());
        } catch (UnauthorizedException e) {
            System.out.println("您没有得到相应的授权！" + e.getMessage());
        }
    }

}
