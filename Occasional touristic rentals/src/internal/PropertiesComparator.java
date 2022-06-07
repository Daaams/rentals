package internal;

import java.util.Comparator;

public class PropertiesComparator implements Comparator<Property> {

    @Override
    public int compare(Property o1, Property o2) {
        return o1.getNameProperty().compareTo(o2.getName());
    }
}
