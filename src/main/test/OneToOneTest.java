import cn.com.taiji.oneToOne.Department;
import cn.com.taiji.oneToOne.Manager;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;


public class OneToOneTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testOneToOne() {
        // 1. 创建EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test2");

        // 2. 创建EntityManager
        EntityManager entityManager = factory.createEntityManager();

        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //4.持久化操作

        //添加数据
        //insert(entityManager);

        //删除数据
        //delete(entityManager);

        //修改数据
        update(entityManager);

        //查询单一对象
        //System.out.println(searchObject(entityManager));

        //查询所有对象
        //System.out.println(searchAll(entityManager));

        // 5. 提交事务
        transaction.commit();

        // 6. 关闭EntityManager
        entityManager.close();

        // 7. 关闭EntityManagerFactory
        factory.close();
    }


    private void insert(EntityManager entityManager) {
        Department department = new Department();
        department.setName("销售部");

        Manager manager = new Manager();
        manager.setName("李四");
        manager.setDepartment(department);

        entityManager.persist(manager);
    }

    private void update(EntityManager entityManager) {
        Manager manager = entityManager.find(Manager.class, 1);
        manager.setName("王五");
        entityManager.merge(manager);
    }


    private Manager searchObject(EntityManager entityManager) {
        return entityManager.createQuery("SELECT m FROM Manager m WHERE id=:id", Manager.class).setParameter("id", 1).getSingleResult();
    }

    private void delete(EntityManager entityManager) {
        Manager manager = entityManager.find(Manager.class, 2);
        entityManager.remove(manager);
    }

    private List searchAll(EntityManager entityManager) {
        return entityManager.createQuery("SELECT m FROM Manager m").getResultList();
    }
}
