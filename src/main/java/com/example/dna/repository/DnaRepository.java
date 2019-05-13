package com.example.dna.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.dna.entity.Dna;

@Repository("dnaRepository")
public interface DnaRepository extends CrudRepository<Dna,Serializable> {
	
	@Query(value="SELECT COUNT(*) FROM dna WHERE n_has_mutation=:hasMutation", nativeQuery=true)
	long countByhasMutation(@Param("hasMutation") int hasMutation);
	
}
