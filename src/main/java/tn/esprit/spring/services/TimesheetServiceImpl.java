package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
@Service
public class TimesheetServiceImpl implements ITimesheetService {


	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;

	public int ajouterMission(Mission mission) {
		try {
			
			missionRepository.save(mission);
			
		} catch(Exception e) {
		}

		return mission.getId();

	}

	public Mission getMissionById(int missionId) {
		try {
			Optional<Mission> m = missionRepository.findById(missionId);
		return m.isPresent() ? m.get() : null;				
		} catch(Exception e) {
		}
		
		return null;
	}

	public int affecterMissionADepartement(int missionId, int depId) {
		try {
			Optional<Mission> m = missionRepository.findById(missionId);
			Optional<Departement> d = departementRepository.findById(depId);
			Departement departement = new Departement();
			Mission mission = new Mission();
			if (m.isPresent()){
				mission = m.get();
			}
			if (d.isPresent())
			{
				departement = d.get();
			}
			mission.setDepartement(departement);
			missionRepository.save(mission);
			return mission.getDepartement().getId();
		}
		catch(Exception e) {
		}
		return -1;

	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);

		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); // par defaut non valide
		timesheetRepository.save(timesheet);


	}

	public int validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {

		Optional<Employe> validateur = employeRepository.findById(validateurId);
		Optional<Mission> mission = missionRepository.findById(missionId);

		Employe e = new Employe();
		Mission m = new Mission();

		if (validateur.isPresent()){
			e = validateur.get();
		}

		if(mission.isPresent())
		{
			 m = mission.get();
		}

		// verifier s'il est un chef de departement (interet des enum)
		if (!e.getRole().equals(Role.CHEF_DEPARTEMENT)) {

			return -1;
		}
		// verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for (Departement dep : e.getDepartements()) {
			if (dep.getId() == m.getDepartement().getId()) {
				chefDeLaMission = true;
				break;
			}
		}
		if (!chefDeLaMission) {
			return 0;
		}
		//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet = timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);

		// Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


		return 1;

	}

	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
