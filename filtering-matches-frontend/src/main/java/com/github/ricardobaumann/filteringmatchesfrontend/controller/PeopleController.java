package com.github.ricardobaumann.filteringmatchesfrontend.controller;

import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.Range;
import com.github.ricardobaumann.filteringmatchesfrontend.services.PeopleReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class PeopleController {

    private final PeopleReportService peopleReportService;

    public PeopleController(PeopleReportService peopleReportService) {
        this.peopleReportService = peopleReportService;
    }

    @GetMapping(value = {"/index", "/"})
    public String index(Model model) {
        model.addAttribute("personFilter", defaultFormData());
        return "filter-form";
    }

    @PostMapping("/filter")
    public String filter(@Valid PersonFilter personFilter,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("personFilter", personFilter);
            return "filter-form";
        }

        log.info("Filtering with {}", personFilter);

        model.addAttribute("people", peopleReportService.filter(personFilter));

        return "result";
    }

    private PersonFilter defaultFormData() {
        return new PersonFilter(
                true,
                true,
                true,
                22.3,
                new Range(10, 20),
                new Range(100, 200),
                new Range(100, 500),
                -1.772232,
                51.568535
        );
    }
}
