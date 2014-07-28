/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.permission;

import db.data.DealerDAO;
import db.data.StoreDAO;
import db.data.UserDAO;
import java.util.ArrayList;
import model.data.DealerRegister;
import model.data.StoreRegister;
import model.data.User;
import model.data.UserRegister;
import model.permission.AdminPerm;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick
 */
public class AdminPermDAOTest {
    
    private static DealerDAO dealerDAO;
    private static DealerRegister dealerRegister;
    private static StoreDAO storeDAO;
    private static StoreRegister storeRegister;
    private static UserDAO userDAO;
    private static UserRegister userRegister;
    private static AdminPermDAO adminPermDAO;
    private static AdminPerm adminPerm;
    private static User u;
    
    
    public AdminPermDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dealerDAO = new DealerDAO();
        dealerRegister = new DealerRegister(dealerDAO);
        storeDAO = new StoreDAO(dealerRegister);
        storeRegister = new StoreRegister(storeDAO);
        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(userDAO);
        adminPermDAO = new AdminPermDAO(userRegister, null);
        
        dealerRegister.load();
        storeRegister.load();
        userRegister.load();
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        u = userRegister.getObjects().get(0);
        adminPerm = new AdminPerm(u);
        adminPermDAO.insert(adminPerm);
    }
    
    @After
    public void tearDown() {
        adminPermDAO.delete(adminPerm);
    }
    
    @Test
    public void testSelect2() {
        System.out.println("select2");
        
        User expResult = adminPerm.getExecutorUser();
        User result = adminPermDAO.select2(u).getExecutorUser();
        assertEquals(expResult, result);
    }
    
}