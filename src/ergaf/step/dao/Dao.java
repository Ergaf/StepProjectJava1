package ergaf.step.dao;

import java.util.List;

public interface Dao <T> {
    public List<T> getAll();
    public T add(T value);
    public void clear();
    public void loadData(List<T> values);
    public boolean delete(T value);
}
