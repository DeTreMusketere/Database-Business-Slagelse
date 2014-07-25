
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Store;
import model.data.User;
import model.data.UserRegister;
import model.permission.Store_UpdatePerm;

/**
 *
 * @author Patrick
 */
public class Store_UpdatePermDAO extends PermissionDAO<Store, Store_UpdatePerm> {

    public Store_UpdatePermDAO(UserRegister userRegister, Register<Store> register) {
        super(userRegister, register);
        table = "update_store_perm";
    }

    @Override
    public void insert(Store_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = source.getTargetStore().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO "+table+" ('target_store_id', 'executor_user_id') VALUES("+targetStoreId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Store_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = source.getTargetStore().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM "+table+" WHERE target_store_id="+targetStoreId+" AND executor_user_id="+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Store_UpdatePerm select(User executor, Store target) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = target.getId();
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE target_store_id="+targetStoreId+" AND executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    Store_UpdatePerm store_UpdatePerm = new Store_UpdatePerm(target, executor);
                    return store_UpdatePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Store_UpdatePerm> select(User executor) {
        ArrayList<Store_UpdatePerm> store_UpdatePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetStoreId = rs.getInt("target_store_id");
                    Store target = register.get(targetStoreId);
                    Store_UpdatePerm store_UpdatePerm = new Store_UpdatePerm(target, executor);
                    store_UpdatePerms.add(store_UpdatePerm);
                }
            }
            return store_UpdatePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
