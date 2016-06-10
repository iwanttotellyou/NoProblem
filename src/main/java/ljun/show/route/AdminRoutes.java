package ljun.show.route;

import com.jfinal.config.Routes;
import ljun.show.controller.*;

/**
 * Created by LJun on 16/5/3.
 */
public class AdminRoutes extends Routes {
    public void config() {
        add("/", IndexController.class);
        add("/api", ApiController.class);
        add("/auth", AuthController.class);
        add("/comment", CommentController.class);
        add("/user", UserController.class);
    }
}
