package eventspider.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

/**
 * @author Sebastian Greenholtz
 */
public class ShiroLogin {
    private Subject currentUser;

    public ShiroLogin() {
       currentUser = SecurityUtils.getSubject();
    }


    public boolean logInAttempt(String username, String password) {
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch ( UnknownAccountException uae ) {
                //username wasn't in the system, show them an error message?
            } catch ( IncorrectCredentialsException ice ) {
                //password didn't match, try again?

            } catch ( AuthenticationException ae ) {
            //unexpected condition - error?
            }
        }
        return true;
    }

    public boolean logOut() {
        currentUser.logout();
        return true;
    }
}
