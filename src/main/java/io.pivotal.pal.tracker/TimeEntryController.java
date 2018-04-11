package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.css.Counter;

import java.util.List;

@RestController
public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;
    private final CounterService counterService;
    private final GaugeService gaugeService;

    public TimeEntryController(TimeEntryRepository repo, CounterService counterService, GaugeService gaugeService) {
        this.timeEntryRepository = repo;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        ResponseEntity<TimeEntry> responseEntity = new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
        counterService.increment("TimeEntry.created");
        gaugeService.submit("timeEntries.count", timeEntryRepository.list().size());
        return responseEntity;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        counterService.increment("TimeEntry.read");
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list()  {
        counterService.increment("TimeEntry.listed");
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {

        TimeEntry entry = timeEntryRepository.update(id, timeEntry);
        if(entry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        counterService.increment("TimeEntry.update");
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        counterService.increment("TimeEntry.delete");
        gaugeService.submit("timeEntries.count", timeEntryRepository.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
