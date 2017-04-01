/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import database.database;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class UserBookingControllerTest {

    public static Connection conn;

    public UserBookingControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {

        conn = database.connect();

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInitialize() {
    }

    @Test
    public void testProcessColumns() {
    }

    @Test
    public void get_user_id() throws Exception {
        
        assertEquals(1, database.get_user_id("11111"));
        
    }
        @Test
    public void check_availableseat() throws Exception {
        
            assertTrue( database.check_availableseat(2));
        
    }
    
           @Test
    public void check_price() throws Exception {
        
            assertTrue( database.check_price(1,100));
        
    }
    @Test
    public void insert() throws Exception {

        assertTrue(database.insert(conn, 1, 1, LocalDate.MAX, 200));

    }

    
    
    
      @Test
    public void update_charge() throws Exception {

        assertTrue(database.update_charge(conn, 20, 1));

    }
    
    
    
}
