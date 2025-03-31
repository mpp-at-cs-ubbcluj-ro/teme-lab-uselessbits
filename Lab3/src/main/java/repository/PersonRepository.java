package repository;
import model.Person;

import java.util.List;

public interface PersonRepository extends Repository<Integer, Person> {
    List<Person> findByName(String manufacturer);
    List<Person> findBetweenYears(int min, int max);
}
