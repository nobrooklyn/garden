package local.garden.async;

public class User implements Cloneable {
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "id=" + id + ", name=" + name;
    }

    @Override
    public User clone() {
        User c = new User(id, name);
        return c;
    }
}