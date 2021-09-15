package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {}
