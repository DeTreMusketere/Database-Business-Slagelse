
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Dealer;
import model.data.Store;
import model.data.User;
import model.data.UserRegister;
import model.permission.User_CreatePerm;

/**
 *
 * @author Patrick
 */
public class User_CreatePermDAO extends PermissionDAO<User, User_CreatePerm> {

    public User_CreatePermDAO(UserRegister userRegister, Register<User> register) {
        super(userRegister, register);
        table = "create_user_perm";
    }

    @Override
    public void insert(User_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();

            User executorUser = source.getExecutorUser();
            Dealer parentDealer = source.getParentDealer();
            Store parentStore = source.getParentStore();
            
            int executorUserId = executorUser.getId();
            int parentDealerId = 0;
            if(parentDealer != null) {
                parentDealerId = parentDealer.getId();
            }
            int parentStoreId = 0;
            if(parentStore != null) {
                parentStoreId = parentStore.getId();
            }

            String sql = "INSERT INTO "+table+" ('executor_user_id', 'parent_store_id', 'parent_dealer_id') VALUES("+executorUserId+", "+parentStoreId+", "+parentDealerId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(User_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();

            User executorUser = source.getExecutorUser();
            Dealer parentDealer = source.getParentDealer();
            Store parentStore = source.getParentStore();
            
            int executorUserId = executorUser.getId();
            int parentDealerId = 0;
            if(parentDealer != null) {
                parentDealerId = parentDealer.getId();
            }
            int parentStoreId = 0;
            if(parentStore != null) {
                parentStoreId = parentStore.getId();
            }

            String sql = "DELETE FROM "+table+" WHERE executor_user_id="+executorUserId+" AND parent_dealer_id="+parentDealerId+" AND parent_store_id="+parentStoreId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User_CreatePerm select(User executor, User target) {
        throw new UnsupportedOperationException("Method not supported");
    }
    
    public User_CreatePerm select2(User executor, Dealer parent) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            int parentDealerId = parent.getId();
            int parentStoreId = 0;
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+" AND parent_dealer_id="+parentDealerId+" AND parent_store_id="+parentStoreId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    User_CreatePerm user_CreatePerm = new User_CreatePerm(executor, parent);
                    return user_CreatePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public User_CreatePerm select2(User executor, Store parent) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            int parentDealerId = parent.getParent().getId();
            int parentStoreId = parent.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+" AND parent_dealer_id="+parentDealerId+" AND parent_store_id="+parentStoreId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    User_CreatePerm user_CreatePerm = new User_CreatePerm(executor, parent);
                    return user_CreatePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User_CreatePerm> select(User executor) {
        throw new UnsupportedOperationException("Method not supported");
    }

}