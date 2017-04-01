/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search_train;

import database.database;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MuHamd Gomaa
 */
public class FXMLDocumentControllerTest {

    static boolean ff = false;
    static boolean rr = false;

    @BeforeClass
    public static void setUpClass() throws SQLException, ClassNotFoundException {
        database ddd = new database();

        ff = ddd.select_trip_validation("cairo", "alex");
        rr = ddd.show_trip_validation(new Time(10, 00, 00));
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
    public void testselect() {

        Assert.assertEquals(true, ff);

    }

    @Test
    public void test_select_time() {
        Assert.assertEquals(true, rr);

    }

}
