package ljun.show.controller;

import com.jfinal.core.Controller;

/**
 * Created by kangbin on 16/4/21.
 */
public abstract class BaseController extends Controller {
    public enum RestError {
        GroupNotExist(1001, "小组不存在");

        private Integer code;
        private String msg;

        RestError(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public static class RestResult {

        public static final RestResult UserNotLogin = new RestResult(1001, "用户未登陆", null);
        public static final RestResult GroupNotExist = new RestResult(1002, "小组不存在", null);
        public static final RestResult UpdateInfoFailed = new RestResult(1003, "更新用户信息失败", null);
        public static final RestResult AuthFailed = new RestResult(1004, "权限不足", null);
        public static final RestResult LoginSuccess = new RestResult(0, "登陆成功", null);
        public static final RestResult FailToHandlePo = new RestResult(2001, "po文操作失败", null);
        public static final RestResult PoNotExist = new RestResult(2002, "po文不存在", null);
        public static final RestResult FailToHandleLike = new RestResult(2003, "点赞操作失败", null);
        public static final RestResult FullTop = new RestResult(2004, "置顶数已满", null);
        public static final RestResult AudioUploadFail = new RestResult(2010, "语音上传失败", null);
        public static final RestResult ShareNotExist = new RestResult(2021, "分享信息不存在", null);
        public static final RestResult FailToHandleComment = new RestResult(3001, "评论操作失败", null);
        public static final RestResult CommentDeleted = new RestResult(3002, "评论已被删除", null);
        public static final RestResult SuccessToHandleComment = new RestResult(0, "评论操作成功", null);
        public static final RestResult ParamsError = new RestResult(4001, "参数错误", null);
        public static final RestResult RequestRrequently = new RestResult(4002, "请求过于频繁", null);

        private Integer code;
        private String msg;
        private Object data;

        public RestResult(Integer code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public Integer getCode() {
            return code;
        }

        public Object getData() {
            return data;
        }

        public String getMsg() {
            return msg;
        }
    }
}
