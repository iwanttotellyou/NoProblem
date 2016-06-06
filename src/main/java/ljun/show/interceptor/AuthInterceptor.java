package ljun.show.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import ljun.show.controller.BaseController;
import ljun.show.model.Auth;
import ljun.show.model.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Check user is login interceptor
 * Created by LJun on 16/6/6.
 */
public class AuthInterceptor implements Interceptor {
    private static final Map<String, Boolean> roleAuth = new HashMap<>();

    static {
        // 拉取所有的权限
        Map<String, String> authMap = new HashMap<>();
        List<Auth> auths = Auth.dao.find("select * from auth");
        for (int i = 0; i < auths.size(); i++) {
            Auth auth = auths.get(i);
            authMap.put(auth.getId() + "", auth.getKey());
        }

        // 拉取所有的角色
        List<Role> roles = Role.dao.find("select * from role");
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            String[] authIds = role.getAuthIds().split(",");

            for (int j = 0; j < authIds.length; j++) {

                if (authMap.containsKey(authIds[j])) {
                    String roleAuthKey = role.getId() + authMap.get(authIds[j]);
                    roleAuth.put(roleAuthKey, Boolean.TRUE);
                }
            }
        }
    }

    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        Integer userId = controller.getSessionAttr("userId");

        // 首先检测用户是否登陆, 如果没有登陆, 返回用户未登陆
        if (userId == null) {
            controller.renderJson(BaseController.RestResult.UserNotLogin);
            return;
        }

        // 其次, 检测用户是否有相应的权限调用接口
        Long roleId = controller.getSessionAttr("roleId");
        String actionKey = inv.getActionKey();
        if (doNotHaveAuth(roleId, actionKey)) {
            controller.renderJson(BaseController.RestResult.AuthFailed);
            return;
        }

        inv.invoke();
    }

    private static Boolean doNotHaveAuth(Long roleId, String actionKey) {
        if (roleId == null) {
            return Boolean.TRUE;
        }

        String roleAuthKey = roleId + actionKey;
        if (!roleAuth.containsKey(roleAuthKey)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
