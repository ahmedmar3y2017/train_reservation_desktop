/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import database.database;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahmed
 */
public class mycontrollerTest {

    static Connection conn;

    public mycontrollerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        conn = database.connect();

    }

    @AfterClass
    public static void tearDownClass() throws SQLException {

        conn.close();

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void check_admin_login() throws Exception {

        assertTrue(database.check_admin_login("123", "123"));

    }

    @Test
    public void get_admin_name() throws SQLException {

        assertEquals("mohamed", database.get_admin_name("123"));

    }

//    @Test
//    public void get_admin_name() throws SQLException {
//
//        assertTrue(database.check_user_login("11111", "11111"));
//
//    }

    @Test
    public void get_user_name() throws SQLException {

        assertEquals("ahmed", database.get_name("11111"));

    }

//    @Test
//    public void get_user_name() throws SQLException {
//
//        assertEquals("ahmed", database.get_name("11111"));
//
//    }

    @Test
    public void insert_user() throws SQLException {

        assertTrue(database.insert_user("aaaaa", "aaaaaaa", "aaaaaaa"));

    }

    @Test
    public void check_user_phone() throws SQLException {

        assertTrue(database.check_user_phone("11111"));

    }

//    @Test
//    public void insert_user() throws SQLException {
//
//        assertTrue(database.insert_user("aaa", "aaa", "aaaaa"));
//
//    }

}
