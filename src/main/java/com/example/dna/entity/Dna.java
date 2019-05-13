package com.example.dna.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="DNA")
public class Dna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="N_ID")
	private Long id;
	
	@Lob
	@Column(name="J_DNA", columnDefinition="json", nullable=false)
	private String dna;
	
	@Column(name="N_HAS_MUTATION", nullable=false, columnDefinition="TINYINT(1) DEFAULT 0")
	private int hasMutation;
	
	public Dna() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public int getHasMutation() {
		return hasMutation;
	}

	public void setHasMutation(int hasMutation) {
		this.hasMutation = hasMutation;
	}

	@Override
	public String toString() {
		return "Dna [id=" + id + ", dna=" + dna + ", hasMutation=" + hasMutation + "]";
	}

}