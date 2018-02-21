package ru.task1.cvfinder.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.task1.cvfinder.model.Resume;

import java.util.List;

/**
 * Created by bumur on 18.02.2018.
 */
public class ResumeDaoImpl implements ResumeDao{
    private static Configuration aConf;
    private static SessionFactory sessionFactory;

    static {
        aConf = new Configuration()
                .addAnnotatedClass(Resume.class);

        sessionFactory = aConf.configure().buildSessionFactory();
    }

    @Override
    public void putResumesIntoDB(List<Resume> resumeList) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Resume resume : resumeList) {
            session.saveOrUpdate(resume);
        }
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteAllResumesFromDB() {
        Session session = sessionFactory.openSession();
        List<Resume> resumeList = getResumesFromDB();
        session.beginTransaction();
        for (Resume resume : resumeList) {
            session.delete(resume);
        }
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<Resume> getResumesFromDB() {
        Session session = sessionFactory.openSession();
        //session.createSQLQuery("SELECT nextval('additional_agreement_id_seq')")
        //Query query = (Query) session.createQuery("from Resume");
        List<Resume> resumeList = session.createCriteria(Resume.class).list();
        session.close();
        return resumeList;
    }

    public static Configuration getaConf() {
        return aConf;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
