package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {

    @Autowired
    private ITimesheetService sTimesheet;
    @Autowired
    private IEmployeService sEmploye;
    @Autowired
    private IEntrepriseService sEntreprise;
    @Autowired
    private TimesheetRepository timesheetRepo;

    /**
     * public void ajouterTimesheet(int missionId, int employeId, Date dateDebut,
     * Date dateFin);
     * 
     * public void validerTimesheet(int missionId, int employeId, Date dateDebut,
     * Date dateFin, int validateurId);
     * 
     */

    @Test
    @Order(1)
    public void testAjouterMission() {
        Mission mission = new Mission("Inspection", "sur terrain");
        int id = sTimesheet.ajouterMission((mission));

        Assert.assertNotNull(sTimesheet.getMissionById(id));
    }

}
