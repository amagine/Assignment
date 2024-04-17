package at.rewe.assignment.application.statistic;

import at.rewe.assignment.entity.Email;
import at.rewe.assignment.infrastructure.persistence.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private final EmailRepository emailRepository;

    @Autowired
    public StatsServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Iterable<Email> retrieveStats() {
        return emailRepository.findAll();
    }

    @Override
    public void saveStats(Email email) {
        emailRepository.save(email);
    }
}
