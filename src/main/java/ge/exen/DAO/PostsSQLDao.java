package ge.exen.DAO;

import ge.exen.dto.PostEditDTO;
import ge.exen.models.Post;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostsSQLDao extends AbstractSQLDAO implements PostsDao{

    @Override
    public void create(Post post) {
        PreparedStatement prStmt;
        try {

            prStmt = conn.prepareStatement("INSERT INTO posts (exam_id, from_id, text, date) VALUES(?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            prStmt.setLong(1, post.getExamId());
            prStmt.setLong(2, post.getFromId());
            prStmt.setString(3, post.getText());
            prStmt.setTimestamp(4, post.getDate());
            int executed = prStmt.executeUpdate();

            if (executed != 0) {
                ResultSet rs = prStmt.getGeneratedKeys();
                rs.next();
                post.setPostId(rs.getLong(1));
            } else {
                throw new SQLException("Post could not be added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            post.setPostId(-1);
        }
    }

    @Override
    public Post getPost(Long postId) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM posts WHERE post_id = ?");
            prStmt.setLong(1, postId);
            ResultSet rs = prStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Post with given PostId could not be retrieved.");
            }
            return resultSetToPost(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Post resultSetToPost(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getLong(1));
        post.setExamId(rs.getLong(2));
        post.setFromId(rs.getLong(3));
        post.setText(rs.getString(4));
        post.setDate(rs.getTimestamp(5));
        return post;
    }

    @Override
    public List<Post> getAllByExamId(Long examId) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM posts WHERE exam_id = ? ORDER BY date DESC");
            prStmt.setLong(1, examId);
            ResultSet rs = prStmt.executeQuery();

            List<Post> posts = new ArrayList<>();
            while (rs.next()) {
                posts.add(resultSetToPost(rs));
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> getAllByPoster(Long fromId) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM posts WHERE from_id = ? ORDER BY date DESC");
            prStmt.setLong(1, fromId);
            ResultSet rs = prStmt.executeQuery();

            List<Post> posts = new ArrayList<>();
            while (rs.next()) {
                posts.add(resultSetToPost(rs));
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePostByID(PostEditDTO postEditDTO) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE posts SET text = ? WHERE post_id = ?");
            st.setString(1, postEditDTO.getNewText());
            st.setLong(2, postEditDTO.getPostId());
            if(st.executeUpdate() == 0) throw new SQLException("something went wrong while updating post");
        } catch (SQLException throwables) {
            postEditDTO.setPostId((long) -1);
        }
    }

    @Override
    public boolean removePostByID(Long postId) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM posts WHERE post_id = ?");
            st.setLong(1, postId);
            return st.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
