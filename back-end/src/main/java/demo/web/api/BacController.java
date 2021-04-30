package demo.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.BacCV;
import demo.entity.NhanVienToaNha;
import demo.repository.BacCVRepository;


@RestController
@RequestMapping(path = "/api/bac", produces = "application/json")
@CrossOrigin(origins = "*")
public class BacController {
	private BacCVRepository bacRepo;
	
	@Autowired
	public BacController(BacCVRepository bacRepo) {
		this.bacRepo = bacRepo;
	}
	
	@GetMapping
	public Iterable<BacCV> getAllBacs() {
		return bacRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public BacCV bacById(@PathVariable("id") Long id) {
		Optional<BacCV> optBac = bacRepo.findById(id);
		if (optBac.isPresent()) {
				return optBac.get();
		}
		return null;	
	}
}
