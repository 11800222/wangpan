package basedao;

public interface BaseDao {
    void save(Object object);
    Object get(String ID);
}
