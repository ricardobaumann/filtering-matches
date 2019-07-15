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
public class PeopleRepository {
    private static final Double RADIUS = 6378.1;
    private final MongoTemplate mongoTemplate;

    public PeopleRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Person save(Person person) {
        return mongoTemplate.save(person);
    }

    public List<Person> findBy(PersonFilter personFilter) {
        Criteria criteria = Criteria.where("city.position")
                .nearSphere(new Point(personFilter.getLongitude(), personFilter.getLatitude()))
                .minDistance(personFilter.getDistanceInKm().getFrom() / RADIUS)
                .maxDistance(personFilter.getDistanceInKm().getTo() / 6378.1);

        Optional.ofNullable(personFilter.getInContact())
                .ifPresent(inContact -> criteria.and("contactsExchanged").gte(0));


        Optional.ofNullable(personFilter.getHasPhoto())
                .ifPresent(hasPhoto -> criteria.and("mainPhoto").exists(true));

        Optional.ofNullable(personFilter.getFavourite())
                .ifPresent(favourite -> criteria.and("favourite").is(favourite));


        Optional.ofNullable(personFilter.getCompatibilityScore())
                .ifPresent(compatibilityScore -> criteria.and("compatibilityScore").gte(compatibilityScore));


        Optional.ofNullable(personFilter.getAgeRange())
                .ifPresent(range -> criteria.and("age").gte(range.getFrom()).lte(range.getTo()));

        Optional.ofNullable(personFilter.getHeightRange())
                .ifPresent(range -> criteria.and("heightInCm").gte(range.getFrom()).lte(range.getTo()));


        return mongoTemplate.find(new Query(criteria), Person.class);
    }


    public void deleteAll() {
        mongoTemplate.remove(new Query(), Person.class);
    }
}
