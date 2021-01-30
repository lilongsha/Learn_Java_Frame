package cn.lilongsha.hibernate.start;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.*;

import javax.persistence.Query;
import java.util.stream.Stream;

/**
 * @Author lilongsha
 * @Description
 * @Date 2021/1/30 4:29 下午
 */
public class StartWithHibernateAPI {
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    @BeforeAll
    public static void beforeAll(){
        serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        session = sessionFactory.openSession();
    }

    @AfterAll
    public static void afterAll(){
        session.close();
        sessionFactory.close();
        serviceRegistry.close();
    }

    @BeforeEach
    public void beforeEach(){
        transaction =  session.beginTransaction();
    }

    @AfterEach
    public void afterEach(){
        transaction.commit();
    }

    @Test
    @DisplayName("start hibernate with HibernateAPI Stream")
    public void startJPAStream() {
        Query query = session.createQuery(
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
