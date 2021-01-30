package cn.lilongsha.hibernate.start;

import cn.lilongsha.hibernate.domain.Person;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author lilongsha
 * @Description
 * @Date 2021/1/30 3:54 下午
 */
public class StartWithJPA {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction transaction;


    @BeforeAll
    public static void beforeAll() {
        entityManagerFactory = Persistence.createEntityManagerFactory("crm");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @AfterAll
    public static void afterAll() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @BeforeEach
    public void beforeEach(){
        transaction.begin();
    }

    @AfterEach
    public void afterEach(){
        transaction.commit();
    }

    @Test
    @DisplayName("start hibernate with JPA")
    public void startJPA() {
        Query query = entityManager.createQuery(
                "select p " +
                        "from Person p " +
                        "where p.name like :name").setParameter("name", "%yi%");
        List<Person> resultList = query.getResultList();
        System.out.println(JSONObject.toJSONString(resultList));
    }

    @Test
    @DisplayName("start hibernate with JPA Stream")
    public void startJPAStream() {
        Query query = entityManager.createQuery(
                "select p " +
                        "from Person p " +
                        "where p.name like :name").setParameter("name", "%yi%");
        Stream resultStream = query.getResultStream();
        resultStream.forEach(p -> {
            System.out.println(p.toString());
        });
        resultStream.close();
    }
}
