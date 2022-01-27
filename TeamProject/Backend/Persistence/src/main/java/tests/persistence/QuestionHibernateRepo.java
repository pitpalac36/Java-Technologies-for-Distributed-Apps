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

import java.util.List;
import java.util.Properties;

public class QuestionHibernateRepo implements IQuestionRepo {
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

    public QuestionHibernateRepo(Properties properties) {
        System.out.println("QuestionHibernateRepo() with properties " + properties);
    }


    @Override
    public List<Question> findAll() {
        initialize();
        try (Session session = sessionFactory.openSession()) {
            try {
                String queryString = "FROM Question";
                List<Question> users =
                        session.createQuery(queryString, Question.class)
                                .list();
                return users;
            } catch (RuntimeException ex) {
                return null;
            } finally {
                close();
            }
        }
    }

    public void disconnect() {

    }

    public boolean isCorrect(int id, boolean answered) {
        initialize();
        try(Session session = sessionFactory.openSession()) {
            try {
                Query query = session.createQuery("FROM Question Q where Q.ID=:id");
                query.setParameter("id", id);
                Question question = (Question) query.uniqueResult();
                return question.isAnswer() == answered;
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            } finally {
                close();
            }
            return false;
        }
    }
}