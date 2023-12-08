package au.com.geekseat.theta.controller;

import au.com.geekseat.theta.service.PersonService;
import au.com.geekseat.theta.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Person>> list() {
        return ResponseEntity.ok().body(personService.findAll());
    }

    @GetMapping("/datatables")
    public ResponseEntity<?> datatables(
            @RequestParam(required = false, value = "itemsPerPage", defaultValue = "10") Long itemsPerPage,
            @RequestParam(required = false, value = "page", defaultValue = "0") Long page,
            @RequestParam(required = false, value = "sortBy", defaultValue = "") List<String> sortBy,
            @RequestParam(required = false, value = "sortDesc", defaultValue = "false") List<Boolean> sortDesc) {
        return ResponseEntity.ok().body(personService.datatables(page, itemsPerPage, sortBy, sortDesc));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok().body(personService.count());
    }

    @GetMapping("/email")
    public ResponseEntity<Person> findByEmail(@RequestParam String email) {
        log.info("Fetching person {}", email);
        return ResponseEntity.ok().body(personService.findByEmail(email));
    }

    @GetMapping("/list/active")
    public ResponseEntity<Iterable<Person>> findByActive(@RequestParam Boolean active) {
        return ResponseEntity.ok().body(personService.findByActive(active));
    }

    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }

    @PostMapping("/trx/demo")
    public ResponseEntity<Person> trxDemo(@RequestBody Person person) throws Exception {
        return new ResponseEntity<>(personService.trxDemo(person), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Person person) {
        if (person.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(personService.save(person), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/inactive/delete")
    public ResponseEntity<?> deleteInactivePerson() {
        return ResponseEntity.ok(personService.deleteInactivePerson());
    }
}