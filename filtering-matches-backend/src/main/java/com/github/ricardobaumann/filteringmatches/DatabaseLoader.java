package com.github.ricardobaumann.filteringmatches;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.ricardobaumann.filteringmatches.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatches.models.City;
import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.service.PeopleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Log4j2
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final PeopleService peopleService;

    public DatabaseLoader(
            ObjectMapper objectMapper,
                          PeopleService peopleService) {
        this.objectMapper = objectMapper;
        this.peopleService = peopleService;
    }

    @Override
    public void run(String... args) throws IOException {
        if (args.length == 0) {
            return;
        }
        Resource databaseSetupResource = new FileSystemResource(args[0]);
        log.info("Loading file {}", databaseSetupResource.getFile().toString());
        JsonNode content = objectMapper.readTree(databaseSetupResource.getFile());
        ArrayNode matches = (ArrayNode) content.get("matches");
        StreamSupport.stream(matches.spliterator(), false)
                .map(jsonNode -> {
                    try {
                        return objectMapper.treeToValue(jsonNode, PersonDto.class);
                    } catch (JsonProcessingException e) {
                        log.error("Failed to parse {}", jsonNode, e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .peek(person -> log.info(person.toString()))
                .map(personDto -> new Person(
                        null,
                        personDto.getDisplayName(),
                        personDto.getAge(),
                        personDto.getJobTitle(),
                        personDto.getHeightInCm(),
                        new City(personDto.getCity().getName(), new GeoJsonPoint(personDto.getCity().getLon(), personDto.getCity().getLat())),
                        personDto.getMainPhoto(),
                        personDto.getCompatibilityScore(),
                        personDto.getContactsExchanged(),
                        personDto.getFavourite(),
                        personDto.getReligion()
                ))
                .forEach(peopleService::save);
    }
}
