package com.study.board.domain.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentUpdate {

    private Long comment_idx;

    private String comment_content;

}
