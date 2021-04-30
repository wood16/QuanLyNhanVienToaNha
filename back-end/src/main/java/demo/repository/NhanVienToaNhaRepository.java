package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.entity.NhanVienToaNha;

@Repository
public interface NhanVienToaNhaRepository extends JpaRepository<NhanVienToaNha, Long>{

}
