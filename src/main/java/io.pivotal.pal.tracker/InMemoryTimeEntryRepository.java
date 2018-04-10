package io.pivotal.pal.tracker;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> timeEntries = new HashMap();

    public TimeEntry create(TimeEntry timeEntry) {
        long newId = timeEntries.size()+1;
        TimeEntry newEntry = new TimeEntry(newId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(newId, newEntry);
        return newEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    public List<TimeEntry> list()  {
        return (List<TimeEntry>) new ArrayList<TimeEntry>(timeEntries.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry newEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(id, newEntry);
        return newEntry;
    }

    public TimeEntry delete(long id) {
        return timeEntries.remove(id);
    }

}
