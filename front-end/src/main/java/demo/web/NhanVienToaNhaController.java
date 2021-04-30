package demo.web;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import demo.BacCV;
import demo.DichVu;
import demo.NhanVienToaNha;
import demo.ViTriCV;



@Controller
@RequestMapping("/nhanvientoanha")
public class NhanVienToaNhaController {
	private RestTemplate rest = new RestTemplate();
	
	

	@GetMapping
	public String showDSNhanVien() {
		return "homeNhanVienToaNha";
	} 
	
	
	// Add NhanVienToaNha
	@GetMapping("/add")
	public String showAddNhanVienToaNhaForm(Model model) {
		
		
		// get DSDichvu, DSVitri, DSBac
		List<DichVu> dichvus =
				Arrays.asList(rest.getForObject("http://localhost:8080/api/dich-vu/",DichVu[].class));
		
		List<ViTriCV> vitris =
				Arrays.asList(rest.getForObject("http://localhost:8080/api/vitri",ViTriCV[].class));
		
		List<BacCV> bacs =
				Arrays.asList(rest.getForObject("http://localhost:8080/api/bac",BacCV[].class));
		
		System.out.print(dichvus);
		model.addAttribute("dsdichvu", dichvus);
		model.addAttribute("dsvitri", vitris);
		model.addAttribute("dsbac", bacs);
		
	
		
		return "addNhanVienToaNha";
	} 
	
	@PostMapping("/add")
	public String addNhanVienToaNha(@RequestParam("ten") String ten, 
									@RequestParam("ngaysinh") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaysinh,
									@RequestParam("sdt")  int sdt,
									@RequestParam("diachi") String diachi,
									@RequestParam("dichvu") Integer idDichvu,
									@RequestParam("vitri") Long idVitri,
									@RequestParam("bac") Long idBac,
									
									Model model) {
		
		
		
		// get dichvu, vitri, bac selected
		DichVu dichvu = rest.getForObject("http://localhost:8080/api/dich-vu/{id}",DichVu.class, idDichvu);
		ViTriCV vitri = rest.getForObject("http://localhost:8080/api/vitri/{id}",ViTriCV.class, idVitri);
		BacCV bac = rest.getForObject("http://localhost:8080/api/bac/{id}",BacCV.class, idBac);
	
		
		
		// add info Nhanvien
		NhanVienToaNha nhanvientoanha = new NhanVienToaNha();
		nhanvientoanha.setTen(ten);
		nhanvientoanha.setDiachi(diachi);
		nhanvientoanha.setSdt(sdt);
		nhanvientoanha.setNgaysinh(ngaysinh);
		nhanvientoanha.setDichvu(dichvu);
		nhanvientoanha.setBac(bac);
		nhanvientoanha.setVitri(vitri);
		
		rest.postForObject("http://localhost:8080/api/nhanvientoanha",nhanvientoanha, NhanVienToaNha.class);
		model.addAttribute("nhanvientoanha", nhanvientoanha);
		
		return "addNhanVienToaNhaSuccess";
	} // End Add NhanVienToaNha
	
	// Search
	@GetMapping("/search")
	public String searchNhanVienToaNha(Model model, @RequestParam("tennhanvien") String ten) throws ParseException {
		List<NhanVienToaNha> nhanvientoanhas =
				Arrays.asList(rest.getForObject("http://localhost:8080/api/nhanvientoanha",NhanVienToaNha[].class));
		List<NhanVienToaNha> search = searchByName(nhanvientoanhas, ten);
		
		model.addAttribute("dsnhanvien", search);

		return "homeNhanVienToaNha";
	}
	
	private List<NhanVienToaNha> searchByName(List<NhanVienToaNha> list, String name) {
		List<NhanVienToaNha> result = new ArrayList<NhanVienToaNha>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getTen().toLowerCase().contains(name)) {
				result.add(list.get(i));
			}
		}
		return result;
	}// end Search
	
	
	// Update and Delete NhanVienToaNha
	@GetMapping("/get")
	public String showNhanVienToaNha(Model model, @RequestParam("idnhanvien") long id) {
		NhanVienToaNha nv = rest.getForObject("http://localhost:8080/api/nhanvientoanha/{id}",NhanVienToaNha.class, id);
		List<DichVu> dichvus =
				Arrays.asList(rest.getForObject("http://localhost:8080/api/dich-vu/",DichVu[].class));
		
		List<ViTriCV> vitris =
				Arrays.asList(rest.getForObject("http://localhost:8080/api/vitri",ViTriCV[].class));
		
		List<BacCV> bacs =
				Arrays.asList(rest.getForObject("http://localhost:8080/api/bac",BacCV[].class));
		
		// Format date
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sm.format(nv.getNgaysinh());
		
		model.addAttribute("ngaysinh", strDate);
		
		model.addAttribute("nhanvien", nv);
		model.addAttribute("dichvu", nv.getDichvu().getTenDv());
		model.addAttribute("vitri", nv.getVitri().getTenvt());
		model.addAttribute("bac", nv.getBac().getHesobac());
		
		model.addAttribute("dsdichvu", dichvus);
		model.addAttribute("dsvitri", vitris);
		model.addAttribute("dsbac", bacs);
		return "getNhanVienToaNha";
	} 
	
	@GetMapping("/delete")
	public String deleteNhanVienToaNha(Model model, @RequestParam("idXoa") long id) {
		rest.delete("http://localhost:8080/api/nhanvientoanha/{id}", id);
		return "deleteNhanVienToaNhaSuccess";
	}
	
	@PostMapping("/update")
	public String updateNhanVienToaNha(	@RequestParam("id") Long id,
										@RequestParam("ten") String ten, 
										@RequestParam("ngaysinh") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaysinh,
										@RequestParam("sdt") int sdt,
										@RequestParam("diachi") String diachi,
										@RequestParam("dichvu") Integer idDichvu,
										@RequestParam("vitri") Long idVitri,
										@RequestParam("bac") Long idBac,
										Model model) {
		// get dichvu, vitri, bac selected
		DichVu dichvu = rest.getForObject("http://localhost:8080/api/dich-vu/{id}",DichVu.class, idDichvu);
		ViTriCV vitri = rest.getForObject("http://localhost:8080/api/vitri/{id}",ViTriCV.class, idVitri);
		BacCV bac = rest.getForObject("http://localhost:8080/api/bac/{id}",BacCV.class, idBac);
				
				
		NhanVienToaNha nhanvientoanha = new NhanVienToaNha();
		nhanvientoanha.setTen(ten);
		nhanvientoanha.setDiachi(diachi);
		nhanvientoanha.setSdt(sdt);
		nhanvientoanha.setNgaysinh(ngaysinh);
		nhanvientoanha.setDichvu(dichvu);
		nhanvientoanha.setVitri(vitri);
		nhanvientoanha.setBac(bac);
		rest.put("http://localhost:8080/api/nhanvientoanha/{id}", nhanvientoanha, id);
		return "updateNhanVienToaNhaSuccess";
	}
	
	
	
}
