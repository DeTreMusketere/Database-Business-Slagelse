
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class TagRegister extends Register<Tag> {

    public TagRegister(DataDAO<Tag> dao) {
        super(dao);
    }
    
    public void create(String name, String description) {
        Tag t = new Tag(0, name, description);
        insert(t);
    }

}
