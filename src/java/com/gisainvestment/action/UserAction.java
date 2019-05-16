package com.gisainvestment.action;

import com.gisainvestment.DAO.UserDAO;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Users;
import com.gisainvestment.security.PasswordEncrypter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

public class UserAction extends ActionSupport implements SessionAware, ModelDriven {

    private static Logger LOGGER = Logger.getLogger(UserAction.class.getName());
    private Map<String, Object> session;
    Map sessionMap = ServletActionContext.getContext().getSession();
    private Users user = new Users();

    static {
        new Log4jConf().loadLog4j();
    }

    //======================= METHODS =============================

    public String login() {
        String forward;
        try {
            Users userFromDb = UserDAO.checkLogin(new Users(user.getUsername(), PasswordEncrypter.securePassword(user.getPassword())));
            if (userFromDb != null) {
                if (userFromDb.getStatus().equals("unlocked")) {
                    switch (userFromDb.getPermission()) {
                        case "administrator":
                            forward = "admin";
                            session.put("user_in_session", userFromDb);
                            break;
                        case "finance":
                            forward = "finance";
                            session.put("user_in_session", userFromDb);
                            break;
                        case "accounting":
                            forward = "accounting";
                            session.put("user_in_session", userFromDb);
                            break;
                        default:
                            forward = ERROR;
                            LOGGER.error("Undefined user");
                            addActionError("ERROR 12000: Undefined user");
                            break;
                    }
                    return forward;
                } else {
                    addActionError("ERROR 12001: Your account has been closed, Please contact the Administrator!");
                    return ERROR;
                }
            } else {
                addActionError("ERROR 12002: Incorrect Username or Password");
                return ERROR;
            }

        } catch (Exception e) {
            LOGGER.error("ERROR 12003" + e);
            addActionError("ERROR 12003: Login failed, Please contact Administrator!");
            return ERROR;
        }

    }

    public String logout() {
        session.clear();
        addActionMessage("Logged out successfully");
        return "logout";
    }

    
    //======================= CONFIG =============================

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public Object getModel() {
        return user;
    }
    //======================= GETTERS AND SETTERS =============================

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        UserAction.LOGGER = LOGGER;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public static void main(String[] args) {
        System.out.println(PasswordEncrypter.securePassword("habineza"));
        System.out.println(PasswordEncrypter.securePassword("peter"));
    }
}
