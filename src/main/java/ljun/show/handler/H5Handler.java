package ljun.show.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LJun on 16/5/25.
 */
public class H5Handler extends Handler {
    @Override
    public void handle(String target, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean[] booleans) {
        if (target.startsWith("/h5")) {
            target = "/";
        }
        next.handle(target, httpServletRequest, httpServletResponse, booleans);
    }
}
