package pl.edu.wat.wcy.swingdynamicgui.form.services;

import java.util.Collection;

public interface SelectOptionProvider<T> {
    Collection<T> getList(Object object, String fieldName);
}
