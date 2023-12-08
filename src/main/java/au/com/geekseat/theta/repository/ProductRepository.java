package au.com.geekseat.theta.repository;

import au.com.geekseat.theta.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
