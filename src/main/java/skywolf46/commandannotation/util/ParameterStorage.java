package skywolf46.commandannotation.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParameterStorage implements Cloneable {
    private HashMap<Class, List<Object>> map = new HashMap<>();
    private HashMap<String, Object> namedVariable = new HashMap<>();

    public ParameterStorage() {
        add(this);
    }

    public static ParameterStorage of(Object... o) {
        ParameterStorage storage = new ParameterStorage();
        for (Object x : o)
            storage.add(x);
        return storage;
    }

    public ParameterStorage add(Object o) {
        ClassUtil.iterateParentClass(PrimitiveConverter.boxPrimitive(o.getClass()), cl -> {
            map.computeIfAbsent(cl, a -> new ArrayList<>()).add(o);
        });
        return this;
    }

    public ParameterStorage add(String name, Object o, boolean addWithClass) {
        if (addWithClass) {
            add(o);
        }
        namedVariable.put(name, o);
        return this;
    }

    public ParameterStorage add(String name, Object o) {
        return add(name, o, false);
    }

    public <T> T get(Class<T> cx) {
        cx = (Class<T>) PrimitiveConverter.boxPrimitive(cx);
        return map.containsKey(cx) ? (T) map.get(cx).get(0) : null;
    }

    public <T> List<T> getAll(Class cx) {
        cx = PrimitiveConverter.boxPrimitive(cx);
        return map.containsKey(cx) ? (List<T>) map.get(cx) : new ArrayList<>();
    }

    public <T> T get(String cx) {
        return (T) namedVariable.get(cx);
    }

    public void set(Object o) {
        ClassUtil.iterateParentClass(o.getClass(), cl -> {
            if (cl.equals(Object.class))
                return;
            List<Object> ri = new ArrayList<>();
            ri.add(o);
            map.put(cl, ri);
        });
    }

    @Override
    public ParameterStorage clone() throws CloneNotSupportedException {
        ParameterStorage storage = new ParameterStorage();
        storage.map = new HashMap<>(map);
        storage.namedVariable = new HashMap<>(namedVariable);
        return storage;
    }
}
