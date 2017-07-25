import bid.woou.ssm.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cc on 2017/7/3.
 */
public class SmTest {
    @Test
    public void addUserTest(){
        ApplicationContext context= new ClassPathXmlApplicationContext("classpath*:config/spring/spring-context.xml");
        UserService userService = (UserService) context.getBean("userService");
      //  userService.addUser();
    }
}
