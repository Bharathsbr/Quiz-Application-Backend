package com.sbr.QuizApp_1.repo;

import com.sbr.QuizApp_1.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category=:category order by rand() limit :noQ" ,nativeQuery = true)
    List<Question> findQuestionByRandomByCategory(String category, int noQ);
}
