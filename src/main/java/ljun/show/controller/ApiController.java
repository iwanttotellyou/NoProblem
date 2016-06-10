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
            Integer userId = getSessionAttr("userId");
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
                    "   WHERE user.id = po.user_id AND po.deleted_time IS NULL) AS polist\n" +
                    "  LEFT JOIN comment\n" +
                    "    ON polist.id = comment.po_id\n" +
                    "WHERE comment.user_id = ?\n", userId);
            renderJson(pos);
            return;
        }

        if ("teacher-po-list".equals(getPara())) {
            List<Po> pos = Po.dao.find("SELECT\n" +
                    "  user.nickname,\n" +
                    "  po.name\n" +
                    "FROM user, po\n" +
                    "WHERE po.user_id = user.id;\n");
            renderJson(pos);
            return;
        }

        if ("own-list".equals(getPara())) {
            Integer userId = getSessionAttr("userId");
            List<Po> pos = Po.dao.find("SELECT\n" +
                    "  user.nickname,\n" +
                    "  po.name,\n" +
                    "  comment.score,\n" +
                    "  comment.id\n" +
                    "FROM user, po, comment\n" +
                    "WHERE user.id = ? AND user.id = po.user_id AND po.id = comment.user_id AND comment.po_id = po.id AND\n" +
                    "      comment.deleted_time IS NULL;\n", userId);
            renderJson(pos);
            return;
        }

        if ("po-content".equals(getPara())) {
            String poId = getPara("poId");
            Integer userId = getSessionAttr("userId");
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
                    "WHERE\n" +
                    "  CASE comment.po_id IS NULL\n" +
                    "  WHEN TRUE\n" +
                    "    THEN COMMENT.deleted_time IS NULL\n" +
                    "  ELSE\n" +
                    "    COMMENT.deleted_time IS NULL AND COMMENT.po_id = ? AND COMMENT.user_id = ?\n" +
                    "  END;", poId, userId);
            renderJson(comments);
            return;
        }
    }
}
