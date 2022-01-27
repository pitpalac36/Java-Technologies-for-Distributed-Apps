package tests.persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import tests.model.Question;
import tests.model.Test;
import tests.persistence.interfaces.IQuestionRepo;
import tests.persistence.interfaces.ITestRepo;

import java.util.List;
import java.util.Properties;

public class TestHibernateRepo implements ITestRepo {
    public static SessionFactory sessionFactory = null;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.out.println(sessionFactory);
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }

    public TestHibernateRepo(Properties properties) {
        System.out.println("TestHibernateRepo() with properties " + properties);
    }


    @Override
    public Test findOne(int id)
    {
        initialize();
        try(Session session = sessionFactory.openSession()) {
            try {
                Query query = session.createQuery("FROM Test T where T.ID=:id");
                query.setParameter("id", id);
                Test test = (Test) query.uniqueResult();
                return test;
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            } finally {
                close();
            }
            return null;
        }
    }

    public void disconnect() {

    }
}