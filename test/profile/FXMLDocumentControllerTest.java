/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile;

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
public class FXMLDocumentControllerTest {

    static Connection conn;

    public FXMLDocumentControllerTest() {
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

//    @Test
//    public void delete_reservation() throws SQLException {
//        
//        
//        assertTrue(database.delete_reservation(1, 1, "2016-11-09", 94));
//        
//    }

    
    
    
//    @Test
//    public void return_phone() {
//        
//        
//        assertEquals("11111",database.return_phone(1));
//        
//    }
    
    
    

//    @Test
//    public void update_phone() {
//        
//        assertTrue(database.update_phone("22222", 1));
//        
//    }
//    





}
