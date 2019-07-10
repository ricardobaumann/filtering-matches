package com.github.ricardobaumann.filteringmatches.repos;

import com.github.ricardobaumann.filteringmatches.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatches.models.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PersonRepository {
    private static final Double RADIUS = 6378.1;
    private final MongoTemplate mongoTemplate;

    public PersonRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Person save(Person person) {
        return mongoTemplate.save(person);
    }

    public List<Person> findBy(PersonFilter personFilter) {
        Criteria criteria = Criteria.where("city.position")
                .nearSphere(new Point(personFilter.getCoordinates()[0], personFilter.getCoordinates()[1]))
                .minDistance(personFilter.getDistanceInKm().getFrom() / RADIUS)
                .maxDistance(personFilter.getDistanceInKm().getTo() / 6378.1);

        Optional.ofNullable(personFilter.getInContact())
                .ifPresent(inContact -> criteria.and("contactsExchanged").gt(0));


        Optional.ofNullable(personFilter.getHasPhoto())
                .ifPresent(hasPhoto -> criteria.and("mainPhoto").exists(true));

        Optional.ofNullable(personFilter.getFavorite())
                .ifPresent(favorite -> criteria.and("favorite").is(favorite));


        Optional.ofNullable(personFilter.getCompatibilityScore())
                .ifPresent(compatibilityScore -> criteria.and("compatibilityScore").gte(compatibilityScore));


        Optional.ofNullable(personFilter.getAgeRange())
                .ifPresent(range -> criteria.and("age").gte(range.getFrom()).lte(range.getTo()));

        Optional.ofNullable(personFilter.getHeightRange())
                .ifPresent(range -> criteria.and("heightInCm").gte(range.getFrom()).lte(range.getTo()));


        return mongoTemplate.find(new Query(criteria), Person.class);
    }


    public void deleteAll() {
        mongoTemplate.remove(Person.class);
    }
}
