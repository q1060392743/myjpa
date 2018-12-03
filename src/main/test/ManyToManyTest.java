/**
 * @author SongChong created by 2018/12/3 0003 21:01
 * @author SongChong created by 2018/12/3 0003 20:14
 * @author SongChong created by 2018/12/3 0003 20:14
 * @author SongChong created by 2018/12/3 0003 20:14
 * @author SongChong created by 2018/12/3 0003 20:14
 * @author SongChong created by 2018/12/3 0003 20:14
 * @author SongChong created by 2018/12/3 0003 20:14
 * @author SongChong created by 2018/12/3 0003 20:14
 */
/**
 * @author SongChong created by 2018/12/3 0003 20:14
 */

import cn.com.taiji.manyToMany.Course;
import cn.com.taiji.manyToMany.Student;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


public class ManyToManyTest {


    @Test
    public void testManyToMany() {
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
        Student student1 = new Student();
        student1.setName("明");

        Student student2 = new Student();
        student2.setName("宁");

        Student student3 = new Student();
        student3.setName("冰");

        Course course1 = new Course();
        course1.setName("c");

        Course course2 = new Course();
        course2.setName("java");

        Course course3 = new Course();
        course3.setName("php");

        List<Course> courseList1 = new ArrayList<>();
        courseList1.add(course1);
        courseList1.add(course2);
        student1.setCourses(courseList1);

        List<Course> courseList2 = new ArrayList<>();
        courseList2.add(course2);
        courseList2.add(course3);
        student2.setCourses(courseList2);

        List<Course> courseList3 = new ArrayList<>();
        courseList3.add(course1);
        courseList3.add(course3);
        student3.setCourses(courseList3);

        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(student1);
        studentList1.add(student3);
        course1.setStudents(studentList1);

        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(student1);
        studentList2.add(student2);
        course1.setStudents(studentList2);

        List<Student> studentList3 = new ArrayList<>();
        studentList3.add(student2);
        studentList3.add(student3);
        course1.setStudents(studentList3);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.persist(course3);

    }

    private void update(EntityManager entityManager) {
        Student student = entityManager.find(Student.class, 2);
        student.setName("影");

        entityManager.merge(student);
    }

    private Student searchObject(EntityManager entityManager) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE id=:id", Student.class).setParameter("id", 1).getSingleResult();
    }


    private void delete(EntityManager entityManager) {
        Student student = entityManager.find(Student.class, 5);
        entityManager.remove(student);
    }

    private List searchAll(EntityManager entityManager) {
        return entityManager.createQuery("SELECT s FROM Student s").getResultList();
    }
}
