
package model.data;

import abstracts.DataDAO;
import abstracts.Register;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class SaleRegister extends Register<Sale> {

    public SaleRegister(IDHandler idHandler, DataDAO<Sale> dao) {
        super(idHandler, dao);
    }
    
    public Sale create(String name, String description, Picture picture, double price, Date start, Date end, Date publish, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextSaleId();
        Sale s = new Sale(id, name, description, picture, price, start, end, publish, parentDealer, updateNumber);
        insert(s);
        return s;
    }
    
    public Sale create(String name, String description, Picture picture, double price, Date start, Date end, Date publish, Store parentStore) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextSaleId();
        Sale s = new Sale(id, name, description, picture, price, start, end, publish, parentStore, updateNumber);
        insert(s);
        return s;
    }

}
