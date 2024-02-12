package com.example.mappingrequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableController {

    @GetMapping("/posts/{id}/commends/{commentId}")
    public String getPostId(
            @PathVariable("id") String postId,
            @PathVariable("commentId") String inputCommendId
    ) {
        return String.format("id : %s  commentId : %s", postId, inputCommendId);
    }

}
