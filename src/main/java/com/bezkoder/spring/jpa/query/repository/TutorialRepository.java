package com.bezkoder.spring.jpa.query.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;

import com.bezkoder.spring.jpa.query.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

  List<Tutorial> findAll();
  
  Optional<Tutorial> findById(long id);
  
  List<Tutorial> findByLevel(int level);
  
  List<Tutorial> findByPublished(boolean isPublished);
  
  List<Tutorial> findByLevelIs(int level);
  
  List<Tutorial> findByLevelEquals(int level);
  
  List<Tutorial> findByLevelNot(int level);
  
  List<Tutorial> findByLevelIsNot(int level);
  
  List<Tutorial> findByLevelAndPublished(int level, boolean isPublished);
  
  List<Tutorial> findByTitleOrDescription(String title, String description);
  
  List<Tutorial> findByTitleLike(String title);
  
  List<Tutorial> findByTitleStartingWith(String title);
  
  List<Tutorial> findByTitleEndingWith(String title);
  
  List<Tutorial> findByTitleContaining(String title);
  
  List<Tutorial> findByTitleContainingIgnoreCase(String title);
  
  List<Tutorial> findByTitleContainingOrDescriptionContaining(String title, String description);
  
  List<Tutorial> findByTitleContainingAndPublished(String title, boolean isPublished);
  
  List<Tutorial> findByPublishedTrue();
  
  List<Tutorial> findByPublishedFalse();
  
  List<Tutorial> findByLevelGreaterThan(int level);
  
  List<Tutorial> findByCreatedAtGreaterThanEqual(Date date);
  
  List<Tutorial> findByCreatedAtAfter(Date date);
  
  List<Tutorial> findByLevelBetween(int start, int end);

  List<Tutorial> findByLevelBetweenAndPublished(int start, int end, boolean isPublished);
  
  List<Tutorial> findByCreatedAtBetween(Date start, Date end);
  
  List<Tutorial> findByOrderByLevel();
  
  List<Tutorial> findByOrderByLevelDesc();
  
  List<Tutorial> findByTitleContainingOrderByLevelDesc(String title);

  List<Tutorial> findByPublishedOrderByCreatedAtDesc(boolean isPublished);
  
  List<Tutorial> findByTitleContaining(String title, Sort sort);
  
  List<Tutorial> findByPublished(boolean isPublished, Sort sort);
  
  List<Tutorial> findTop3ByTitleContainingAndPublished(String title, boolean isPublished);
  
  Page<Tutorial> findAll(Pageable pageable);
  
  Page<Tutorial> findByTitleContaining(String title, Pageable pageable);

  @Transactional
  void deleteAllByCreatedAtBefore(Date date);
}