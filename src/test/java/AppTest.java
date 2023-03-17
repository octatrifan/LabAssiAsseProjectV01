import org.junit.jupiter.api.Test;
import repository.*;
import service.*;
import validation.*;
import org.junit.Before;

public class AppTest {
    StudentValidator vs;
    TemaValidator vt;
    NotaValidator vn;
    StudentXMLRepository strepo;
    TemaXMLRepository tmrepo;
    NotaXMLRepository ntrepo;
    Service service;

    @Before
    public void initData() {
        vs = new StudentValidator();
        vt = new TemaValidator();
        vn = new NotaValidator();
        strepo = new StudentXMLRepository(vs, "studentitest.xml");
        tmrepo = new TemaXMLRepository(vt, "temetest.xml");
        ntrepo = new NotaXMLRepository(vn, "notetest.xml");
        service = new Service(strepo, tmrepo, ntrepo);
    }

    @Test
    public void testAddStudentCorrect() {
        initData();
        int result;
        try {
            result = service.saveStudent("13", "Octavian Trifan", 937);
        } catch (Exception ve) {
            result = 1;
        }
        assert (result == 0);
    }

    @Test
    public void testAddStudentWrongGroup() {
        initData();
        int result;
        try {
            result = service.saveStudent("113", "Octavian Trifan", 999);
        } catch (Exception ve) {
            result = 0;
        }
        assert (result == 1);
    }
}


