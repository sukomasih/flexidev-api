package au.com.geekseat.theta.service;

import au.com.geekseat.theta.model.Person;
import au.com.geekseat.theta.model.Role;
import au.com.geekseat.theta.repository.PersonRepository;
import au.com.geekseat.theta.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.*;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;

    public void addRoleToPerson(String email, String roleName) {
        log.info("Adding role {} to person {}", roleName, email);
        Person person = personRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        person.getRoles().add(role);
    }

    @Transactional(propagation = REQUIRED)
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
