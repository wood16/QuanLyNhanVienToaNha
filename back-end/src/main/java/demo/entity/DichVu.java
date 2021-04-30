package demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "dichvu")
public class DichVu implements Serializable {

	@Id
	private Integer dichVuId;

	
	private String tenDv;

	
	private String loaiDv;

	
	private String heSoLuong;
	
	private double donGia;
	
	// OneToMany bị repeat data
	
	@OneToMany(
	    mappedBy = "dichvu",
	    orphanRemoval = true,
	    fetch = FetchType.LAZY,
	    cascade = CascadeType.ALL
	)
    @JsonIgnoreProperties("dichvu")
	private List<NhanVienToaNha> nhanvientoanhas = new ArrayList<>();
}
