package org.lab1.ro;

import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.*;
import service.*;
import validation.*;
import org.junit.Before;

import java.util.Objects;
import java.util.stream.StreamSupport;

public class Lab3Test {
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
    public void testAddHomeworkNullID() {
        initData();
        int result;
        try {
            result = service.saveTema(null, "homework1", 10, 11);
        } catch (Exception ve) {
            result = 0;
        }
        assert (result == 1);
    }

    @Test
    public void testAddHomeworkEmptyID() {
        initData();
        int result;
        try {
            result = service.saveTema("", "homework1", 10, 11);
        } catch (Exception ve) {
            result = 0;
        }
        assert (result == 1);
    }

    @Test
    public void testAddHomeworkValidID() {
        initData();
        int result;
        try {
            result = service.saveTema("15", "homework1", 11, 10);
            Iterable<Tema> all = service.findAllTeme();
            boolean res = StreamSupport.stream(all.spliterator(), false)
                    .anyMatch(x -> Objects.equals(x.getID(), "15") && x.getDescriere().equals("homework1") && x.getDeadline() == 11 && x.getStartline() == 10);
            assert (res);
        } catch (Exception ve) {
            result = 1;
        }
        assert (result == 0);
    }
}


