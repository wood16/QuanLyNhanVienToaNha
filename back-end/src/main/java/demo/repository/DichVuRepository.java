package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.entity.DichVu;
@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Integer>{

}
