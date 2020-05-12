package pl.edu.wat.wcy.swingdynamicgui.examples.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
