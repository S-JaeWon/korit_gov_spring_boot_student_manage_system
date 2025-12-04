package com.korit.student_manage_system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentJournal {
    private Integer studentJournalId;
    private Integer userId;
    private String journalContent;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
