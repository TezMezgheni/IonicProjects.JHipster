package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TimeSheet;
import com.mycompany.myapp.repository.TimeSheetRepository;
import org.springframework.stereotype.Service;

@Service
public class TimeSheetService {

    private final TimeSheetRepository timeSheetRepository;

    public TimeSheetService(TimeSheetRepository timeSheetRepository) {
        this.timeSheetRepository = timeSheetRepository;
    }

    public TimeSheet createTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.save(timeSheet);
        return timeSheet;
    }

    public void deleteTimeSheet(TimeSheet timeSheet) {
        timeSheetRepository.delete(timeSheet);
    }

    public TimeSheet getTimeSheet(long timeSheetId) {
        return timeSheetRepository.findById(timeSheetId).get();
    }
}
