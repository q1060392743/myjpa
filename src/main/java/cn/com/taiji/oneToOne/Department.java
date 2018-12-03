package cn.com.taiji.oneToOne;

import javax.persistence.*;

/**
 * @author SongChong created by 2018/12/3 0003 16:01
 */
@Entity
@Table(name = "DEPT")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private Manager manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


}