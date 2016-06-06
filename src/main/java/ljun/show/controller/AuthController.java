package ljun.show.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cocotyty on 16-3-14.
 */
public class AuthController extends Controller {
    static final ConcurrentHashMap<String, String> auths = new ConcurrentHashMap<String, String>();

    static {
        auths.put("ljun", "ljun");
    }

    public void index() {
        this.createToken("token");
        this.render("/auth/login.html");
    }

    public void login() {
        if (!this.validateToken("token")) {
            redirect("/auth/");
            return;
        }
        String name = this.getPara("user");
        String password = this.getPara("password");
        if (StrKit.isBlank(name) || StrKit.isBlank(password)) {
            System.out.println("post wrong");
            redirect("/auth/");
            return;
        }
        if (!password.equals(auths.get(name))) {
            System.out.println("password wrong");
            redirect("/auth/");
            return;
        }
        setSessionAttr("user", name);
        redirect("/");
    }
}
