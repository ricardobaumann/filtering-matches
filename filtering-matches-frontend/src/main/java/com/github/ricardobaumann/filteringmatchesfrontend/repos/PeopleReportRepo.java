package com.github.ricardobaumann.filteringmatchesfrontend.repos;

import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonFilter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "backend", url = "http://localhost:8081")
public interface PeopleReportRepo {

    @PostMapping("/people/report")
    List<PersonDto> createReport(PersonFilter personFilter);
}
