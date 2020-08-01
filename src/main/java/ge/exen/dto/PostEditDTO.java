package ge.exen.dto;

import javax.validation.constraints.NotNull;

/**
 * This class is what we expect from post as an input when the user edits his/her post
 */
public class PostEditDTO {
    @NotNull
    private Long postId;
    @NotNull
    private String newText;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }
}
