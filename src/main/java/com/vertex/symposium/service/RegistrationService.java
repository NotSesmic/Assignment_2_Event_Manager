package com.vertex.symposium.service;

import com.vertex.symposium.model.Registration;
import com.vertex.symposium.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }
}