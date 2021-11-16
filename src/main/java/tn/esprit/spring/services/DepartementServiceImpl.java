package tn.esprit.spring.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {


	@Autowired
	DepartementRepository deptRepoistory;
	public static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);


	public List<Departement> getAllDepartements() {
		l.info("getalldepartements");
		return (List<Departement>) deptRepoistory.findAll();
		
	}

}
