package at.rewe.assignment.application.statistic;

import at.rewe.assignment.entity.Email;
import org.springframework.stereotype.Service;

@Service
public interface StatsService {
    Iterable<Email> retrieveStats();
    void saveStats(Email email);
}
