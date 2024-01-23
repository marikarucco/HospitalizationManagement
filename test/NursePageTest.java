import com.hospitalizationmanagement.domainmodel.HospitalizationRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.hospitalizationmanagement.businesslogic.NursePage;
import com.hospitalizationmanagement.dao.PatientDao;
import com.hospitalizationmanagement.dao.PatientDaoImpl;
import com.hospitalizationmanagement.domainmodel.Patient;

import java.sql.SQLException;
import java.time.LocalDate;


public class NursePageTest {
   private static NursePage np;
   private static Patient p1;
   private static PatientDao pd;

   @BeforeAll
   static void setUp(){
      np = new NursePage(12);
      p1 = new Patient(1, "Mario", "Bianchi",LocalDate.of(1960, 11, 21) , "M",null);
      p1.setHospitalizationRecord(new HospitalizationRecord(1));
      pd = new PatientDaoImpl();
   }

   @Test
   public void testAssignBed(){
      np.assignBed(p1);
      assertFalse(p1.getBed().isAvailable());
      Patient p2 = new Patient(2, "Franca", "Verdi", LocalDate.of(1962, 6, 18), "F", null);
      np.assignBed(p2);
      assertFalse(np.getBeds().get(0).isAvailable());
      assertFalse(np.getBeds().get(1).isAvailable());
   }

   @Test
   public void testFreeBed(){
      np.assignBed(p1);
      np.freeBed(p1.getBed());
      assertTrue(p1.getBed().isAvailable());
   }

   @Test
   public void testSavePatient() throws SQLException {
      np.savePatient(2, "Franca", "Verdi", LocalDate.of(1962, 10, 8), "F");
      assertEquals(pd.getPatientByID(2).getName(), "Franca");
      assertNotEquals(pd.getPatientByID(2).getSurname(),"Bianchi");
   }

   @Test
   public void testDeletePatient() throws SQLException{
      np.deletePatient(p1);
      assertNotEquals(pd.getAllPatients().size(),2);
   }

   @AfterAll
   static void cleanUp(){
      pd.deletePatient(1);
      pd.deletePatient(2);
   }

}
