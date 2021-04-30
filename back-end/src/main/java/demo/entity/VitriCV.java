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
@Table(name = "vitricv")
public class VitriCV implements Serializable{

	@Id
	private Long id;
	
	private String tenvt;
	
	private int hesoluong;
	
	@OneToMany(
		    mappedBy = "vitri",
		    orphanRemoval = true,
		    fetch = FetchType.LAZY,
		    cascade = CascadeType.ALL
		)
	@JsonIgnoreProperties("vitri")
	private List<NhanVienToaNha> nhanvientoanhas = new ArrayList<>();
}
