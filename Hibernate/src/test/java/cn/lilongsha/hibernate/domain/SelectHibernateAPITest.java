package cn.lilongsha.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * @Author lilongsha
 * @Description Hibernate select api
 * @Date 2021/1/30 7:45 下午
 */
public class SelectHibernateAPITest {
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
}
