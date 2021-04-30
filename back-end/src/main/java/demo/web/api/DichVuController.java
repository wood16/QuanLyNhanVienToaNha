package demo.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.DichVu;
import demo.repository.DichVuRepository;

@RestController
@RequestMapping(path = "/api/dich-vu", produces = "application/json")
@CrossOrigin(origins = "*")
public class DichVuController {
	private DichVuRepository dichvuRepo;
	
	@Autowired
	public DichVuController(DichVuRepository dichvuRepo) {
		this.dichvuRepo = dichvuRepo;
	}
	
	@GetMapping("/")
	public Iterable<DichVu> getAllDichVus() {
		return dichvuRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public DichVu dichvuById(@PathVariable("id") Integer id) {
		Optional<DichVu> optDichVu = dichvuRepo.findById(id);
		if (optDichVu.isPresent()) {
				return optDichVu.get();
		}
		return null;
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public DichVu postDichVu(@RequestBody DichVu
	dichvu) {
		return dichvuRepo.save(dichvu);
	}
}
