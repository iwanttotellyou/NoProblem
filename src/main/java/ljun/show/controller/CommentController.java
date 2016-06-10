package ljun.show.controller;

import com.jfinal.plugin.activerecord.Db;
import ljun.show.model.Comment;
import ljun.show.model.Po;

import java.util.List;

/**
 * Created by LJun on 16/6/7.
 */
public class CommentController extends BaseController {
    public void teacher() {
        Comment comment = getModel(Comment.class, "", true);

        //创建评论先检查po状态
        List<Po> pos = Po.dao.find("SELECT *\n" +
                "FROM po\n" +
                "WHERE id = ? AND deleted_time IS NULL\n" +
                "ORDER BY updated_time DESC", comment.getPoId());
        if (pos.size() < 1) {
            //po文已被删除
            renderJson(RestResult.PoNotExist);
            return;
        }

        List<Comment> comments = Comment.dao.find("SELECT id\n" +
                "FROM comment\n" +
                "WHERE po_id = ? AND user_id = ? AND deleted_time IS NULL;", comment.getPoId(), getSessionAttr("userId"));
        if (comments.size() < 1) {
            comment.save();
            renderJson(new BaseController.RestResult(0, "", comment.getId()));
            return;
        }

        try {
            Db.update("UPDATE comment\n" +
                    "SET teacher_say = ?, score = ?\n" +
                    "WHERE id = ?;", comment.getTeacherSay(), comment.getScore(), comments.get(0).getId());
        } catch (Exception e) {
            renderJson(RestResult.FailToHandleComment);
            return;
        }
        renderJson(new BaseController.RestResult(0, "", comment.getId()));
    }

    public void student() {
        Comment comment = getModel(Comment.class, "", true);

        //创建评论先检查po状态
        List<Po> pos = Po.dao.find("SELECT *\n" +
                "FROM po\n" +
                "WHERE id = ? AND deleted_time IS NULL\n" +
                "ORDER BY updated_time DESC", comment.getPoId());
        if (pos.size() < 1) {
            //po文已被删除
            renderJson(RestResult.PoNotExist);
            return;
        }

        List<Comment> comments = Comment.dao.find("SELECT id\n" +
                "FROM comment\n" +
                "WHERE po_id = ? AND user_id = ? AND deleted_time IS NULL;", comment.getPoId(), getSessionAttr("userId"));
        if (comments.size() < 1) {
            comment.save();
            renderJson(new BaseController.RestResult(0, "", comment.getId()));
            return;
        }

        try {
            Db.update("UPDATE comment\n" +
                    "SET student_say = ?\n" +
                    "WHERE id = ?;", comment.getStudentSay(), comments.get(0).getId());
        } catch (Exception e) {
            renderJson(RestResult.FailToHandleComment);
            return;
        }
        renderJson(new BaseController.RestResult(0, "", comment.getId()));
    }
}
