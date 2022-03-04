package com.bezkoder.spring.jpa.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.bezkoder.spring.jpa.query.model.Tutorial;
import com.bezkoder.spring.jpa.query.repository.TutorialRepository;

@SpringBootApplication
public class SpringJpaRepositoryQueryExampleApplication implements CommandLineRunner {

  @Autowired
  TutorialRepository tutorialRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringJpaRepositoryQueryExampleApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    tutorialRepository.deleteAll();

    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11");
    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-26");
    Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-19");

    tutorialRepository.save(new Tutorial("Spring Data", "Spring Data Description", 3, true, date1));
    tutorialRepository.save(new Tutorial("Java Spring Boot", "Spring Framework Description", 1, false, date1));
    tutorialRepository.save(new Tutorial("Hibernate", "Hibernate ORM Description", 3, true, date2));
    tutorialRepository.save(new Tutorial("Spring Boot", "Spring Boot Description", 2, false, date2));
    tutorialRepository.save(new Tutorial("Spring JPA", "Spring Data JPA Description", 3, true, date3));
    tutorialRepository.save(new Tutorial("Spring Batch", "Spring Batch Description", 4, true, date3));
    tutorialRepository.save(new Tutorial("Spring Security", "Spring Security Description", 5, false, date3));

    List<Tutorial> tutorials = new ArrayList<>();

    tutorials = tutorialRepository.findAll();
    show(tutorials);

    Tutorial tutorial = tutorialRepository.findById(1).get();
    System.out.println(tutorial);

    tutorials = tutorialRepository.findByPublished(true);
    show(tutorials);

    tutorials = tutorialRepository.findByLevel(3);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelIs(3);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelEquals(3);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelNot(3);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelIsNot(3);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelAndPublished(3, true);
    show(tutorials);

    tutorials = tutorialRepository.findByTitleOrDescription("Hibernate", "Spring Data Description");
    show(tutorials);

    tutorials = tutorialRepository.findByTitleStartingWith("Spring");
    show(tutorials);

    tutorials = tutorialRepository.findByTitleEndingWith("ot");
    show(tutorials);

    tutorials = tutorialRepository.findByTitleContaining("at");
    show(tutorials);

    tutorials = tutorialRepository.findByTitleContainingIgnoreCase("dat");
    show(tutorials);

    String text = "ot";
    tutorials = tutorialRepository.findByTitleContainingOrDescriptionContaining(text, text);
    show(tutorials);

    tutorials = tutorialRepository.findByTitleContainingAndPublished("ring", true);
    show(tutorials);

    tutorials = tutorialRepository.findByPublishedTrue();
    show(tutorials);

    tutorials = tutorialRepository.findByPublishedFalse();
    show(tutorials);

    tutorials = tutorialRepository.findByLevelGreaterThan(3);
    show(tutorials);

    Date myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

    tutorials = tutorialRepository.findByCreatedAtGreaterThanEqual(myDate);
    show(tutorials);

    tutorials = tutorialRepository.findByCreatedAtAfter(myDate);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelBetween(3, 5);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelBetweenAndPublished(3, 5, true);
    show(tutorials);

    Date myDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-11");
    Date myDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

    tutorials = tutorialRepository.findByCreatedAtBetween(myDate1, myDate2);
    show(tutorials);

    tutorials = tutorialRepository.findByOrderByLevel();
    show(tutorials);

    tutorials = tutorialRepository.findByOrderByLevelDesc();
    show(tutorials);

    tutorials = tutorialRepository.findByTitleContainingOrderByLevelDesc("at");
    show(tutorials);

    tutorials = tutorialRepository.findByPublishedOrderByCreatedAtDesc(true);
    show(tutorials);

    tutorials = tutorialRepository.findByTitleContaining("at", Sort.by("level").descending());
    show(tutorials);

    tutorials = tutorialRepository.findByPublished(false, Sort.by("level").descending());
    show(tutorials);

    List<Order> orders = new ArrayList<Order>();
    orders.add(new Order(Sort.Direction.DESC, "level"));
    orders.add(new Order(Sort.Direction.ASC, "title"));

    tutorials = tutorialRepository.findByPublished(true, Sort.by(orders));
    show(tutorials);

    int page = 0;
    int size = 3;

    Pageable pageable = PageRequest.of(page, size);

    tutorials = tutorialRepository.findAll(pageable).getContent();
    show(tutorials);

    tutorials = tutorialRepository.findByTitleContaining("ring", pageable).getContent();
    show(tutorials);

    pageable = PageRequest.of(page, size, Sort.by("level").descending());

    tutorials = tutorialRepository.findAll(pageable).getContent();
    show(tutorials);

    pageable = PageRequest.of(page, size, Sort.by("level").descending());

    tutorials = tutorialRepository.findByTitleContaining("at", pageable).getContent();
    show(tutorials);

    Date outdate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-1");

    tutorialRepository.deleteAllByCreatedAtBefore(outdate);
    tutorials = tutorialRepository.findAll();
    show(tutorials);
  }

  private void show(List<Tutorial> tutorials) {
    tutorials.forEach(System.out::println);
  }
}
