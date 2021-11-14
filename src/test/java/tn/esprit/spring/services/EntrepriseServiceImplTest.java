package tn.esprit.spring.services;


import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)

@SpringBootTest

public class EntrepriseServiceImplTest {
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Autowired
	IEntrepriseService es;
	@Autowired
	EntrepriseRepository er;
	@Autowired
	DepartementRepository deptRepoistory;
	

	
	
	@Test
 	public void testAjouterEntreprise() {
		try {
			Entreprise e = new Entreprise(1, "esprit");
			int id = es.ajouterEntreprise(e);
			assertNotNull(id);
			es.deleteEntrepriseById(id);
			l.info("End add Entreprise test method");
			
		}
		catch(NullPointerException e) {
			l.error("erreur d'ajout entreprise"+e.getMessage());
		}
		
	}
	
	@Test
	public void testAjouterDepartement() {
	
		try {
			Departement d = new Departement("Info");
			 deptRepoistory.save(d);
			 int id =d.getId();
			assertNotNull(id);
			deptRepoistory.deleteById(id);
			l.info("End add Department test method");
			} catch (NullPointerException e) {
				l.error("erreur d'ajout departement "+e.getMessage());
			}
		}
		
	
	
	@Test()
	public void testGetEntrepriseById() {
		try {

		Entreprise getEntreprise = es.getEntrepriseById(3);
		l.info("End get Entreprise test method");
			}
		 catch (NullPointerException e) {
				l.error("error get entreprise "+e.getMessage());
			}
		}
	
	
	
	
	
}

	
	
	
	

