package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;



@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {

		try {

			deptRepoistory.save(dep);


		} catch (Exception e) {
		}

		return dep.getId();
	}
	
	public int affecterDepartementAEntreprise(int depId, int entrepriseId) {
		Departement dep = new Departement();
		try {

			Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);
			Optional<Departement> d = deptRepoistory.findById(depId);

			if (e.isPresent() && d.isPresent()) {

				Entreprise entrepriseManagedEntity = e.get();

				Departement depManagedEntity = d.get();

				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);

				dep = depManagedEntity;



			}
		 else {
		}

		} catch (Exception e) {
		}

		return dep.getEntreprise().getId();
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		List<String> depNames = new ArrayList<>();
		Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);
	
		for(String elem: depNames)
	       {
	       }
		

		if (e.isPresent()) {
			Entreprise entrepriseManagedEntity = e.get();

			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}

			
			for(String elem: depNames)
		       {
		       }

			return depNames;
		}
		

		
		
		

		return depNames;

	}
	
	@Transactional
	public int deleteEntrepriseById(int entrepriseId) {
		Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);

		try {

			if (e.isPresent()) {
				

				entrepriseRepoistory.delete(e.get());
				
				return 1;
			} else {
				return -1;
			}

		} catch (Exception err) {

		}
		if(e.isPresent()) {
		}
	

		return 0;

	}

	@Transactional
	public int deleteDepartementById(int depId) {
		Optional<Departement> d = deptRepoistory.findById(depId);

		try {

			if (d.isPresent()) {

				deptRepoistory.delete(d.get());
				return 1;
			} else {

				return -1;
			}

		} catch (Exception e) {

		}
		if(d.isPresent()) {
		}
		return 0;

	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		try {
			Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);
			if (e.isPresent()) {

				return e.get();
			}
		} catch (Exception e) {

		}

		return null;
	}

	
	public Departement getDepartementById(int departementId) {
		try {
			Optional<Departement> d = deptRepoistory.findById(departementId);

			if (d.isPresent()) {


				return d.get();
			}
		} catch (Exception e) {

		}

		return null;
	}

}
