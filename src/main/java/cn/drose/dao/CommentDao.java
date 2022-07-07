package cn.drose.dao;

import cn.drose.dto.cond.CommentCond;
import cn.drose.model.CommentDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 评论实体类
 * Created by Donghua.Chen on 2018/4/29.
 */
@Mapper
@Component
public interface CommentDao {

    /**
     * 新增评论
     * @param commentDomain
     * @return
     */
    int addComment(CommentDomain commentDomain);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int deleteComment(@Param("id") Integer id);

    /**
     * 更新评论的状态
     * @param id
     * @return
     */
    int updateCommentStatus(@Param("id") Integer id, @Param("status") String status);

    /**
     * 获取单条评论
     * @param id
     * @return
     */
    CommentDomain getCommentById(@Param("id") Integer id);
    /**
     * 根据文章编号获取评论列表
     * @param cid
     * @return
     */
    List<CommentDomain> getCommentsByCId(@Param("cid") Integer cid);

    /**
     * 根据条件获取评论列表
     * @param commentCond
     * @return
     */
    List<CommentDomain> getCommentsByCond(CommentCond commentCond);

    /**
     * 获取文章数量
     * @return
     */
    Long getCommentsCount();
}
