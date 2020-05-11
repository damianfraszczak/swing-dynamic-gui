package pl.edy.wat.wcy.dynamicgui.form.services;

import java.util.Collection;

public interface SelectOptionProvider<T> {
    Collection<T> getList(Object object, String fieldName);
}
