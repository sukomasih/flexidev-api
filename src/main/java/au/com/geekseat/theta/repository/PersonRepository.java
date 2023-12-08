package au.com.geekseat.theta.repository;

import au.com.geekseat.theta.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);

    Iterable<Person> findByActive(Boolean active);

    @Modifying
    @Query("delete from Person where active = false")
    int deleteInactivePerson();

    int deleteByActive(Boolean active);
}
