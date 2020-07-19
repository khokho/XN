package ge.exen.dto;

import javax.validation.constraints.NotNull;

/**
 * This class is what we expect from post as an input when the user edits his/her post
 */
public class PostEditDTO {
    @NotNull
    private Long postID;
    @NotNull
    private String newText;

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }
}
