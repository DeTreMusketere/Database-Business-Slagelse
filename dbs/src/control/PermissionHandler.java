
package control;

import db.DBTool;
import db.permission.*;
import java.util.ArrayList;
import model.data.User;
import model.permission.*;

/**
 *
 * @author Patrick
 */
public class PermissionHandler {
    
    private final AdminPermDAO adminPermDAO;
    
    private final Dealer_AdminPermDAO dealer_AdminPermDAO;
    private final Dealer_CreatePermDAO dealer_CreatePermDAO;
    private final Dealer_DeletePermDAO dealer_DeletePermDAO;
    private final Dealer_ReadPermDAO dealer_ReadPermDAO;
    private final Dealer_UpdatePermDAO dealer_UpdatePermDAO;
    
    private final Product_CreatePermDAO product_CreatePermDAO;
    private final Product_DeletePermDAO product_DeletePermDAO;
    private final Product_ReadPermDAO product_ReadPermDAO;
    private final Product_UpdatePermDAO product_UpdatePermDAO;
    
    private final Sale_CreatePermDAO sale_CreatePermDAO;
    private final Sale_DeletePermDAO sale_DeletePermDAO;
    private final Sale_ReadPermDAO sale_ReadPermDAO;
    private final Sale_UpdatePermDAO sale_UpdatePermDAO;
    
    private final Store_AdminPermDAO store_AdminPermDAO;
    private final Store_CreatePermDAO store_CreatePermDAO;
    private final Store_DeletePermDAO store_DeletePermDAO;
    private final Store_ReadPermDAO store_ReadPermDAO;
    private final Store_UpdatePermDAO store_UpdatePermDAO;
    
    private final User_CreatePermDAO user_CreatePermDAO;
    private final User_DeletePermDAO user_DeletePermDAO;
    private final User_ReadPermDAO user_ReadPermDAO;
    private final User_UpdatePermDAO user_UpdatePermDAO;

    public PermissionHandler(AdminPermDAO adminPermDAO, Dealer_AdminPermDAO dealer_AdminPermDAO, Dealer_CreatePermDAO dealer_CreatePermDAO, Dealer_DeletePermDAO dealer_DeletePermDAO, Dealer_ReadPermDAO dealer_ReadPermDAO, Dealer_UpdatePermDAO dealer_UpdatePermDAO, Product_CreatePermDAO product_CreatePermDAO, Product_DeletePermDAO product_DeletePermDAO, Product_ReadPermDAO product_ReadPermDAO, Product_UpdatePermDAO product_UpdatePermDAO, Sale_CreatePermDAO sale_CreatePermDAO, Sale_DeletePermDAO sale_DeletePermDAO, Sale_ReadPermDAO sale_ReadPermDAO, Sale_UpdatePermDAO sale_UpdatePermDAO, Store_AdminPermDAO store_AdminPermDAO, Store_CreatePermDAO store_CreatePermDAO, Store_DeletePermDAO store_DeletePermDAO, Store_ReadPermDAO store_ReadPermDAO, Store_UpdatePermDAO store_UpdatePermDAO, User_CreatePermDAO user_CreatePermDAO, User_DeletePermDAO user_DeletePermDAO, User_ReadPermDAO user_ReadPermDAO, User_UpdatePermDAO user_UpdatePermDAO) {
        this.adminPermDAO = adminPermDAO;
        this.dealer_AdminPermDAO = dealer_AdminPermDAO;
        this.dealer_CreatePermDAO = dealer_CreatePermDAO;
        this.dealer_DeletePermDAO = dealer_DeletePermDAO;
        this.dealer_ReadPermDAO = dealer_ReadPermDAO;
        this.dealer_UpdatePermDAO = dealer_UpdatePermDAO;
        this.product_CreatePermDAO = product_CreatePermDAO;
        this.product_DeletePermDAO = product_DeletePermDAO;
        this.product_ReadPermDAO = product_ReadPermDAO;
        this.product_UpdatePermDAO = product_UpdatePermDAO;
        this.sale_CreatePermDAO = sale_CreatePermDAO;
        this.sale_DeletePermDAO = sale_DeletePermDAO;
        this.sale_ReadPermDAO = sale_ReadPermDAO;
        this.sale_UpdatePermDAO = sale_UpdatePermDAO;
        this.store_AdminPermDAO = store_AdminPermDAO;
        this.store_CreatePermDAO = store_CreatePermDAO;
        this.store_DeletePermDAO = store_DeletePermDAO;
        this.store_ReadPermDAO = store_ReadPermDAO;
        this.store_UpdatePermDAO = store_UpdatePermDAO;
        this.user_CreatePermDAO = user_CreatePermDAO;
        this.user_DeletePermDAO = user_DeletePermDAO;
        this.user_ReadPermDAO = user_ReadPermDAO;
        this.user_UpdatePermDAO = user_UpdatePermDAO;
    }
    
    
    
    public UserPermissionSet constructUserPermissionSet(User u) {
        UserPermissionSet ups = new UserPermissionSet();
        
        //Admin
        AdminPerm adminperm;
        
        //Dealer
        ArrayList<Dealer_AdminPerm> dealer_AdminPerm = dealer_AdminPermDAO.select(u);
        Dealer_CreatePerm dealer_CreatePerm = dealer_CreatePermDAO.select2(u);
        ArrayList<Dealer_ReadPerm> dealer_ReadPerms = dealer_ReadPermDAO.select(u);
        ArrayList<Dealer_UpdatePerm> dealer_UpdatePerms = dealer_UpdatePermDAO.select(u);
        ArrayList<Dealer_DeletePerm> dealer_DeletePerms = dealer_DeletePermDAO.select(u);
        
        //Store
        ArrayList<Store_AdminPerm> store_AdminPerms = store_AdminPermDAO.select(u);
        ArrayList<Store_CreatePerm> store_CreatePerms = store_CreatePermDAO.select(u);
        ArrayList<Store_ReadPerm> store_ReadPerms = store_ReadPermDAO.select(u);
        ArrayList<Store_UpdatePerm> store_UpdatePerms = store_UpdatePermDAO.select(u);
        ArrayList<Store_DeletePerm> store_DeletePerms = store_DeletePermDAO.select(u);
        
        //User
        ArrayList<User_CreatePerm> user_CreatePerms = user_CreatePermDAO.select(u);
        ArrayList<User_ReadPerm> user_ReadPerms = user_ReadPermDAO.select(u);
        ArrayList<User_UpdatePerm> user_UpdatePerms = user_UpdatePermDAO.select(u);
        ArrayList<User_DeletePerm> user_DeletePerms = user_DeletePermDAO.select(u);
        
        //product
        ArrayList<Product_CreatePerm> product_CreatePerms;
        ArrayList<Product_ReadPerm> product_ReadPerms = product_ReadPermDAO.select(u);
        ArrayList<Product_UpdatePerm> product_UpdatePerms = product_UpdatePermDAO.select(u);
        ArrayList<Product_DeletePerm> product_DeletePerms = product_DeletePermDAO.select(u);
        
        //Sale
        ArrayList<Sale_CreatePerm> sale_CreatePerms;
        ArrayList<Sale_ReadPerm> sale_ReadPerms = sale_ReadPermDAO.select(u);
        ArrayList<Sale_UpdatePerm> sale_UpdatePerms = sale_UpdatePermDAO.select(u);
        ArrayList<Sale_DeletePerm> sale_DeletePerms = sale_DeletePermDAO.select(u);
        
        
        DBTool.close();
        return ups;
    }

}
