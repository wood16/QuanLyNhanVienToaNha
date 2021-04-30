package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.entity.BacCV;
@Repository
public interface BacCVRepository extends JpaRepository<BacCV, Long>{

}
