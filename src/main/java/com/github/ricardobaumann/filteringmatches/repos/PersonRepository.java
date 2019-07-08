package com.github.ricardobaumann.filteringmatches.repos;

import com.github.ricardobaumann.filteringmatches.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
}
