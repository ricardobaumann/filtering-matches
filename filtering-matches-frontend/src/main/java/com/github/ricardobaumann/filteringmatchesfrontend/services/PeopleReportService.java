package com.github.ricardobaumann.filteringmatchesfrontend.services;

import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatchesfrontend.repos.PeopleReportRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleReportService {

    private final PeopleReportRepo peopleReportRepo;

    public PeopleReportService(PeopleReportRepo peopleReportRepo) {
        this.peopleReportRepo = peopleReportRepo;
    }

    public List<PersonDto> filter(PersonFilter personFilter) {
        return peopleReportRepo.createReport(personFilter);
    }
}
