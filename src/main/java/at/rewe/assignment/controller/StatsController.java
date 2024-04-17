package at.rewe.assignment.controller;

import at.rewe.assignment.application.statistic.StatsServiceImpl;
import at.rewe.assignment.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email/statistic")
public class StatsController {

    private final StatsServiceImpl statsServiceImpl;

    @Autowired
    public StatsController(StatsServiceImpl statsServiceImpl) {
        this.statsServiceImpl = statsServiceImpl;
    }

    @GetMapping
    public Iterable<Email> getEmailStats() {
        return statsServiceImpl.retrieveStats();
    }
}
