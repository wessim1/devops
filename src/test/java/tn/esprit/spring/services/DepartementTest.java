package tn.esprit.spring.services;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementTest {

	@Autowired
	IEntrepriseService serviceEntreprise;


	@Test
	public void testAjouterDepartment() {
		Departement dep = new Departement("Web");
		int id = serviceEntreprise.ajouterDepartement(dep);
		Assert.assertNotNull(serviceEntreprise.getDepartementById(id));
		
	}


	
}