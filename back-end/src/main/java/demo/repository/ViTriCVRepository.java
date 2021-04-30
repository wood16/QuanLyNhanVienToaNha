package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.entity.VitriCV;
@Repository
public interface ViTriCVRepository extends JpaRepository<VitriCV, Long>{

}
