package au.com.geekseat.theta.repository;

import au.com.geekseat.theta.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
