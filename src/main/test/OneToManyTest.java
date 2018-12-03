/**
 * @author SongChong created by 2018/12/3 0003 20:14
 */

import cn.com.taiji.oneToMany.Company;
import cn.com.taiji.oneToMany.Employee;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


public class OneToManyTest {


    @Test
    public void testOneToMany() {
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
        //update(entityManager);

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
        Company company = new Company();
        company.setName("ali");

        Employee employee1 = new Employee();
        employee1.setName("mary");
        employee1.setCompany(company);

        Employee employee2 = new Employee();
        employee2.setName("lucy");
        employee2.setCompany(company);


        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        company.setEmployees(employeeList);

        entityManager.persist(company);
    }

    private void update(EntityManager entityManager) {
        Employee employee = entityManager.find(Employee.class, 1);
        Company company = entityManager.find(Company.class, 6);
        employee.setCompany(company);

        entityManager.merge(employee);

    }


    private Company searchObject(EntityManager entityManager) {
        return entityManager.createQuery("SELECT c FROM Company c WHERE id=:id", Company.class).setParameter("id", 5).getSingleResult();
    }


    private void delete(EntityManager entityManager) {
        Employee employee = entityManager.find(Employee.class, 3);

        entityManager.remove(employee);
    }

    private List searchAll(EntityManager entityManager) {
        return entityManager.createQuery("SELECT c FROM Company c").getResultList();
    }
}
