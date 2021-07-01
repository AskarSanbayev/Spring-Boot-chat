package com.chat.cyber.comp;

import com.chat.cyber.model.Comment;
import com.chat.cyber.model.ContentFile;
import com.chat.cyber.model.Post;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class PermissionHelper {

    @PreAuthorize("#authorId == #post.authorId")
    public void checkPostEditPermission(Long authorId, Post post) {
    }

    @PreAuthorize("#authorId == #comment.authorId")
    public void checkCommentEditPermission(Long authorId, Comment comment) {
    }

    @PreAuthorize("#authorId == #contentFile.userId")
    public void checkFileEditPermission(Long authorId, ContentFile contentFile) {
    }
}
