package internal;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorProperties implements Iterator<Property> {

    List<Property> properties;
    int i = 0;

    public IteratorProperties(List<Property> list) {
        properties = list;
    }

    @Override
    public boolean hasNext() {
        return properties != null && properties.size() > i;
    }

    @Override
    public Property next() {
        return properties.get(i++);
    }
}
