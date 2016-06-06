package ljun.show.controller;

import com.jfinal.core.Controller;
import ljun.show.model.Comment;
import ljun.show.model.Po;

import java.util.List;

/**
 * Created by LJun on 16/6/5.
 */
public class ApiController extends Controller {
    public void index() {
        // 获取首页信息
        if ("po-list".equals(getPara())) {
            List<Po> pos = Po.dao.find("SELECT\n" +
                    "  polist.teacher,\n" +
                    "  polist.id,\n" +
                    "  polist.name,\n" +
                    "  comment.score\n" +
                    "FROM\n" +
                    "  (SELECT\n" +
                    "     user.nickname AS teacher,\n" +
                    "     po.id         AS id,\n" +
                    "     po.name       AS name\n" +
                    "   FROM user, po\n" +
                    "   WHERE user.id = po.user_id) AS polist\n" +
                    "  LEFT JOIN comment\n" +
                    "    ON polist.id = comment.po_id;");
            renderJson(pos);
            return;
        }

        if ("po-content".equals(getPara())) {
            String poId = getPara("poId");
            String userId = getPara("userId");
            List<Comment> comments = Comment.dao.find("SELECT\n" +
                    "  comment.id,\n" +
                    "  po.name,\n" +
                    "  po.title,\n" +
                    "  po.subtitle,\n" +
                    "  po.content,\n" +
                    "  comment.score,\n" +
                    "  comment.teacher_say,\n" +
                    "  comment.student_say\n" +
                    "FROM po\n" +
                    "  LEFT JOIN comment\n" +
                    "    ON po.id = comment.po_id\n" +
                    "WHERE comment.po_id = ? AND comment.user_id = ?;\n", poId, userId);
            renderJson(comments);
            return;
        }
    }
}
