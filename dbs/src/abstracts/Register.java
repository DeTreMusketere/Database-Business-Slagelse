package abstracts;

import java.util.ArrayList;
import java.util.Iterator;
import model.data.IDHandler;
import model.data.UpdateNumberHandler;

/**
 *
 * @author Patrick
 * @param <DATATYPE>
 */
public abstract class Register<DATATYPE extends Data> {

    private ArrayList<DATATYPE> objects;
    private final DataDAO<DATATYPE> dao;
    protected final IDHandler idHandler;

    public Register(IDHandler idHandler, DataDAO<DATATYPE> dao) {
        this.idHandler = idHandler;
        this.dao = dao;
        objects = new ArrayList<>();
    }

    /**
     * Inserts a source object
     *
     * @param source
     */
    public void insert(DATATYPE source) {
        objects.add(source);
        dao.insert(source);
        dao.close();
    }

    /**
     * Updates a target with a source
     *
     * @param source
     * @param target
     */
    public void update(DATATYPE source, DATATYPE target) {
        int updateNumber = UpdateNumberHandler.update();
        source.setUpdateNumber(updateNumber);
        objects.set(objects.indexOf(target), source);
        dao.update(source, target);
        dao.close();
    }

    /**
     * Deletes a target
     *
     * @param target
     */
    public void delete(DATATYPE target) {
        objects.remove(target);
        dao.delete(target);
        dao.close();
    }

    /**
     * Gets a target based on ID
     *
     * @param id
     * @return DATATYPE
     */
    public DATATYPE get(int id) {
        Iterator<DATATYPE> i = objects.iterator();

        while (i.hasNext()) {
            DATATYPE object = i.next();
            Data d = (Data) object;
            if (d.getId() == id) {
                return object;
            }
        }
        return null;
    }

    /**
     * Loads all objects to memory
     */
    public void load() {
        objects = dao.selectAll();
    }

    /**
     * Gets all objects
     *
     * @return ArrayList<DATATYPE>
     */
    public ArrayList<DATATYPE> getObjects() {
        return objects;
    }

    /**
     * Sets all objects
     *
     * @param objects
     */
    public void setObjects(ArrayList<DATATYPE> objects) {
        this.objects = objects;
    }

}
