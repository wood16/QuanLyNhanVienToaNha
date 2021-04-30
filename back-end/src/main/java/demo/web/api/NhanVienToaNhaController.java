package demo.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.NhanVienToaNha;
import demo.repository.NhanVienToaNhaRepository;

@Transactional
@RestController
@RequestMapping(path = "/api/nhanvientoanha", produces = "application/json")
@CrossOrigin(origins = "*")
public class NhanVienToaNhaController {
	private NhanVienToaNhaRepository nhanvienRepo;
	
	@Autowired
	public NhanVienToaNhaController(NhanVienToaNhaRepository nhanvienRepo) {
		this.nhanvienRepo = nhanvienRepo;
	}
	
	@GetMapping
	public Iterable<NhanVienToaNha> getAllNhanVienToaNhas() {
		return nhanvienRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public NhanVienToaNha nhanvientoanhaById(@PathVariable("id") Long id) {
		Optional<NhanVienToaNha> optNhanVienToaNha = nhanvienRepo.findById(id);
		if (optNhanVienToaNha.isPresent()) {
				return optNhanVienToaNha.get();
		}
		return null;
		
	}
	
	// @PathVariable("id")    ????
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public NhanVienToaNha updateNhanvienToaNha(@PathVariable("id") Long id, @RequestBody NhanVienToaNha
	nhanvientoanha) {
		nhanvientoanha.setId(id);
		return nhanvienRepo.save(nhanvientoanha);
		
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteNhanvienById(@PathVariable("id") Long id) {
		nhanvienRepo.deleteById(id);
		
	}
	
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public NhanVienToaNha postNhanVienToaNha(@RequestBody NhanVienToaNha
	nhanvientoanha) {
		return nhanvienRepo.save(nhanvientoanha);
	}
	
	
}
