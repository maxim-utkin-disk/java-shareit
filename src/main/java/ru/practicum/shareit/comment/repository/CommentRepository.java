package ru.practicum.shareit.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.comment.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByItemId(Long itemId);

    @Query("select c " +
            "from comment as c " +
            "where c.item.id in (?1) " +
            "order by c.created DESC")
    List<Comment> findByItemIn(List<Long> ids);
}