import com.hospitalizationmanagement.businesslogic.ResidentPage;
import com.hospitalizationmanagement.businesslogic.SpecialistPage;
import com.hospitalizationmanagement.dao.HospitalizationRecordDao;
import com.hospitalizationmanagement.dao.HospitalizationRecordDaoImpl;
import com.hospitalizationmanagement.domainmodel.Bed;
import com.hospitalizationmanagement.domainmodel.HospitalizationRecord;
import com.hospitalizationmanagement.domainmodel.Patient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidentPageTest {
    private static ResidentPage rp;
    private static SpecialistPage sp;
    private static Patient p1;
    private static HospitalizationRecordDao hrd;

    @BeforeAll
    static void setUp(){
        rp = new ResidentPage(1, "Mario", "Rossi");
        sp = new SpecialistPage(1, "Giovanni", "Bianchi", rp);
        p1 = new Patient(1, "Mario", "Bianchi",LocalDate.of(1960, 11, 21) , "M",new Bed(1));
        hrd = new HospitalizationRecordDaoImpl();
    }


    @Test
    public void testAssignHospitalizationRecord(){
        rp.assignHospitalizationRecord(1, p1);
        assertEquals(p1.getHospitalizationRecord().getRecordID(), 1);
        assertEquals(p1.getHospitalizationRecord().getPatient(), p1);
    }

    @Test
    public void testSaveHospitalizationRecord() throws SQLException {
        HospitalizationRecord hr = new HospitalizationRecord(1, p1);
        rp.saveHospitalizationRecord(hr);
        assertEquals(hrd.getRecordByID(1).getPatient().getPatientID(), p1.getPatientID());
        assertEquals(hrd.getAllRecords().size(), 1);
    }

    @Test
    public void testCreateDailyCheck() throws IllegalArgumentException{
        rp.createDailyCheck(1, p1, LocalDate.now(), 36, 85, 75, 120, 60, "stranguria");
        assertEquals(sp.getChecks().get(0). getTemperature(), 36);
        assertEquals(sp.getChecks().get(0).getOxygenSaturation(), 85);
        assertEquals(sp.getChecks().get(0).getDiastolicPressure(), 75);
        assertEquals(sp.getChecks().get(0).getSystolicPressure(), 120);
        rp.createDailyCheck(2, p1, LocalDate.now(), 38, 75, 60, 100, 80, "dolori addominali");
        assertEquals(sp.getChecks().size(), 2);
    }

    @AfterAll
    static void cleanUp(){
        hrd.deleteRecord(1);
    }
}

