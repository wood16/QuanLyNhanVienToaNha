package demo.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.VitriCV;
import demo.repository.ViTriCVRepository;



@RestController
@RequestMapping(path = "/api/vitri", produces = "application/json")
@CrossOrigin(origins = "*")
public class ViTriController {
	private ViTriCVRepository vitriRepo;
	
	@Autowired
	public ViTriController(ViTriCVRepository vitriRepo) {
		this.vitriRepo = vitriRepo;
	}
	
	@GetMapping
	public Iterable<VitriCV> getAllViTris() {
		return vitriRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public VitriCV vitriCVById(@PathVariable("id") Long id) {
		Optional<VitriCV> optViTri = vitriRepo.findById(id);
		if (optViTri.isPresent()) {
				return optViTri.get();
		}
		return null;
		
	}
}
