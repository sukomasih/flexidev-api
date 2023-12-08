package au.com.geekseat.theta.repository;

import au.com.geekseat.theta.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
