package pl.edy.wat.wcy.dynamicgui.enums;

public enum FieldSize {
    ONE(12), TWO(6), THREE(4), FOUR(3), SIX(2);
    private int size;

    FieldSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }


}
