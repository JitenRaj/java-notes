package DesignPatterns.Creational.Prototype;

public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    // getters
    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }

    // The core method of the Prototype pattern
    public Object clone() {
        Object clone = null;
        try {
            // super.clone() performs a shallow copy
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

