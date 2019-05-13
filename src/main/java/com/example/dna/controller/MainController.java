package com.example.dna.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.dna.entity.Dna;
import com.example.dna.repository.DnaRepository;
import com.example.dna.service.DnaService;
import com.example.dna.service.Utl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@CrossOrigin
@RequestMapping(value="/")
public class MainController {
	private JsonParser jsonParser = new JsonParser();
	
	@Autowired
	private DnaRepository dnaRepository;
	
	@PostMapping(value="mutation")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public ResponseEntity dnaMutation(@RequestBody String string, Principal principal) {
		JsonObject json = jsonParser.parse(string).getAsJsonObject();
		JsonArray array = json.get("dna").getAsJsonArray();
		String[] dna = new String[array.size()];
		int idx = 0;
		for(JsonElement elm : array) {
			dna[idx++] = elm.getAsString();
		}
		boolean hasMutation = new DnaService().hasMutation(dna);
		ResponseEntity response = new ResponseEntity(hasMutation?HttpStatus.OK:HttpStatus.FORBIDDEN);
		Dna record = new Dna();
		record.setDna(""+json);
		record.setHasMutation(hasMutation?1:0);
		dnaRepository.save(record);
		return(response);
	}
	
	@RequestMapping(value="stats", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String getStats() {
		long mutations = dnaRepository.countByhasMutation(1);
		long noMutations = dnaRepository.countByhasMutation(0);
		double ratio = Utl.round(Double.parseDouble(""+mutations)/Double.parseDouble(""+noMutations),2);
		JsonObject response = new JsonObject();
		response.addProperty("count_mutations",mutations);
		response.addProperty("count_no_mutations",noMutations);
		response.addProperty("ratio",ratio);
		return(""+response);
	}
	
	@GetMapping(value="status")
	@ResponseBody
	public String checkStatus() {
		return("ok");
	}
	
}
