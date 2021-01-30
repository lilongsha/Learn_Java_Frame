package cn.lilongsha.hibernate.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

/**
 * @Author lilongsha
 * @Description JPA select api
 * @Date 2021/1/30 2:50 下午
 */
public class SelectAPITest {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;


    @BeforeAll
    public static void beforeAll(){
        entityManagerFactory = Persistence.createEntityManagerFactory("crm");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void afterAll(){
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("get a JPA Query")
    public void getQuery(){
        Query query = entityManager.createQuery(
                "select p " +
                        "from Person p " +
                        "where p.name like :name").setParameter("name", "%yi%");
        List<Person> resultList = query.getResultList();
        this.printObjectList(resultList);
    }

    @Test
    @DisplayName("get a TypedQuery")
    public void getTypedQuery(){
        TypedQuery<Person> typedQuery = entityManager.createQuery("select p from Person  p where p.name like :name", Person.class).setParameter("name", "%liyi%");
        List<Person> resultList = typedQuery.getResultList();
        this.printObjectList(resultList);
    }

    @Test
    @DisplayName("get a NamedQuery JPA Query")
    public void getNamedQueryJAPQuery(){
        Query query = entityManager.createNamedQuery("get_person_by_name").setParameter("name", "liyiyi");
        List resultList = query.getResultList();
        this.printObjectList(resultList);
    }

    @Test
    @DisplayName("get a NamedQuery TypedQuery")
    public void getNamedQueryTypedQuery(){
        Query query = entityManager.createNamedQuery("get_person_by_name", Person.class).setParameter("name", "liyiyi");
        List resultList = query.getResultList();
        this.printObjectList(resultList);
    }



    private void printObjectList(List objects){
        for (Object o : objects) {
            System.out.println(o.toString());
        }
    }
}
