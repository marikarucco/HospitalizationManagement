import com.hospitalizationmanagement.businesslogic.ResidentPage;
import com.hospitalizationmanagement.businesslogic.SpecialistPage;
import com.hospitalizationmanagement.dao.DailyCheckDao;
import com.hospitalizationmanagement.dao.DailyCheckDaoImpl;
import com.hospitalizationmanagement.domainmodel.Bed;
import com.hospitalizationmanagement.domainmodel.DailyCheck;
import com.hospitalizationmanagement.domainmodel.HospitalizationRecord;
import com.hospitalizationmanagement.domainmodel.Patient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SpecialistPageTest {
    private static ResidentPage rp;
    private static SpecialistPage sp;
    private static DailyCheckDao dcd;
    private static Patient p1;
    private static HospitalizationRecord hr;

    @BeforeAll
    static void setUp(){
        rp = new ResidentPage(1, "Mario", "Rossi");
        sp = new SpecialistPage(1, "Dario", "Verdi", rp);
        dcd = new DailyCheckDaoImpl();
        p1 = new Patient(1, "Mario", "Bianchi",LocalDate.of(1960, 11, 21) , "M",new Bed(1));
        hr = new HospitalizationRecord(1, p1);
        rp.assignHospitalizationRecord(1, p1);
    }

    @Test
    public void testConfirmCheck() throws IllegalArgumentException{
        DailyCheck dc = new DailyCheck(1, p1, sp.getSpecialist(), LocalDate.now(), 36, 85, 75, 120, 60, "stranguria");
        sp.confirmCheck(dc);
        assertEquals(p1.getHospitalizationRecord().getChecks().get(0).getTemperature(), 36);
        assertNotEquals(p1.getHospitalizationRecord().getChecks().get(0).getDoctor().getName(), "Giovanni");
    }

    @Test
    public void testAddCheck() throws SQLException, IllegalArgumentException {
        DailyCheck dc = new DailyCheck(2, p1, sp.getSpecialist(), LocalDate.now(), 38, 75, 60, 100, 80, "dolori addominali");
        sp.addCheck(dc);
        assertEquals(dcd.getRecordByID(2).getHeartRate(), 80);
        assertEquals(dcd.getAllRecords(p1).size(), 1);
    }

    @AfterAll
    static void cleanUp(){
        dcd.deleteRecord(2);
    }
}

