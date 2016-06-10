package ljun.show.controller;

import ljun.show.model.User;

import java.util.List;

/**
 * Created by LJun on 16/6/9.
 */
public class UserController extends BaseController {
    public void login() {
        String account = getPara("account");
        String password = getPara("password");
        List<User> users = User.dao.find("SELECT\n" +
                "  id,\n" +
                "  role_id,\n" +
                "  nickname\n" +
                "FROM user\n" +
                "WHERE account = ? AND password = ?;", account, password);
        if (users.size() < 1) {
//            renderJson(new RestResult(1, "用户名或密码错误", null));
            redirect("/login");
            return;
        }
        User user = users.get(0);
        setSessionAttr("userId", user.getId());
        setSessionAttr("roleId", user.getRoleId());
        setSessionAttr("userName", user.getNickname());
        setCookie("role", String.valueOf(user.getRoleId()), 3600);
//        renderJson(user);
        redirect("/h5/index");
    }
}
