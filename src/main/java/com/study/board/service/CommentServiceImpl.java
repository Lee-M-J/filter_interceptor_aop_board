package com.study.board.service;

import com.study.board.domain.comment.Comment;
import com.study.board.domain.comment.CommentLikeDislike;
import com.study.board.domain.comment.CommentUpdate;
import com.study.board.domain.comment.CommentWrite;
import com.study.board.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void writeComment(CommentWrite commentWrite) {
        commentMapper.insertComment(commentWrite);
    }

    @Override
    public List<Comment> findComments(Long board_idx) {
        return commentMapper.findComments(board_idx);
    }

    @Override
    public void deleteComment(Long comment_idx) {
        commentMapper.deleteComment(comment_idx);
    }

    @Override
    public void updateComment(CommentUpdate commentUpdate) {
        commentMapper.updateComment(commentUpdate);
    }

    @Override
    public void likeComment(CommentLikeDislike commentLikeDislike) {
        int isAlreadyLike = commentMapper.checkLikeDislike(commentLikeDislike);
        if (isAlreadyLike == 1) {
            commentMapper.deleteLikeAndDislike(commentLikeDislike);
        } else if (isAlreadyLike == 0) {
            commentMapper.insertLikeAndDislike(commentLikeDislike);
        }
        commentMapper.updateCommentLike(commentLikeDislike.getComment_idx());
    }

    @Override
    public void dislikeComment(CommentLikeDislike commentLikeDislike) {
        int isAlreadyDislike = commentMapper.checkLikeDislike(commentLikeDislike);
        if (isAlreadyDislike == 1) {
            commentMapper.deleteLikeAndDislike(commentLikeDislike);
        } else if (isAlreadyDislike == 0) {
            commentMapper.insertLikeAndDislike(commentLikeDislike);
        }
        commentMapper.updateCommentDislike(commentLikeDislike.getComment_idx());
    }
}