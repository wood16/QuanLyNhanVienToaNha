package demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "nhanvientoanha")
public class NhanVienToaNha implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ten;
	
	private Date ngaysinh;
	
	private int sdt;
	
	private String diachi;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dichvu_id")
	@JsonIgnoreProperties("nhanviendiachis") 
    private DichVu dichvu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vitricv_id")
	@JsonIgnoreProperties("nhanviendiachis") 
    private VitriCV vitri;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "baccv_id")
	@JsonIgnoreProperties("nhanviendiachis") 
    private BacCV bac;
}
