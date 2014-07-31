
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class DealerRegister extends Register<Dealer> {

    public DealerRegister(DataDAO<Dealer> dao) {
        super(dao);
    }
    
    public void create(String name, String description, String phone, Picture picture) {
        Dealer d = new Dealer(0, name, description, phone, picture);
        int id = insert(d);
        d.setId(id);
    }


}
