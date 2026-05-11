package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {

    List<EmailNotification> findByUserId(Long userId);

    List<EmailNotification> findByJobProfileId(Long jobProfileId);
}
