package si.fri.prpo.skupina20;

import java.io.Serializable;

public abstract class Entiteta implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
