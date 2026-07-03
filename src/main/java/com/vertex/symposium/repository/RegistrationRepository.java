package com.vertex.symposium.repository;

import com.vertex.symposium.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventNameOrderByCreatedAtDesc(String eventName);
    List<Registration> findAllByOrderByCreatedAtDesc();
}
