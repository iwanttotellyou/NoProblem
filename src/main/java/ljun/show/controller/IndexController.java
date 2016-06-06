package ljun.show.controller;

import com.jfinal.core.Controller;

/**
 * Created by LJun on 16/6/5.
 */
public class IndexController extends Controller {
    public void index() {
            this.render("/index.html");
    }
}
