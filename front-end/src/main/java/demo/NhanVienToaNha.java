package demo;






import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@NoArgsConstructor
@Data
@AllArgsConstructor
public class NhanVienToaNha {

	private Long id;
	
	private String ten;
	
	private Date ngaysinh;
	
	private int sdt;
	
	private String diachi;
	
    private DichVu dichvu;
	
    private ViTriCV vitri;
	
    private BacCV bac;
}
