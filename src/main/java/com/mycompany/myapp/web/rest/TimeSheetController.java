package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TimeSheet;
import com.mycompany.myapp.service.TimeSheetService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSheetController {

    private final TimeSheetService timeSheetService;

    public TimeSheetController(TimeSheetService timeSheetService) {
        this.timeSheetService = timeSheetService;
    }

    @PostMapping(path = "api/timesheet/new")
    public TimeSheet addNewTimeSheet(TimeSheet timeSheet) {
        return timeSheetService.createTimeSheet(timeSheet);
    }

    @GetMapping(path = "api/timesheet/get")
    public TimeSheet findTimeSheet(TimeSheet timeSheet) {
        return timeSheetService.getTimeSheet(timeSheet.getId());
    }

    @DeleteMapping(path = "api/timesheet/delete")
    public void DeleteTimeSheet(TimeSheet timeSheet) {
        timeSheetService.deleteTimeSheet(timeSheet);
    }
}
