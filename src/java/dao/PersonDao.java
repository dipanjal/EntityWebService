/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.Person;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Dipanjal
 */
public class PersonDao {
    
    public PersonDao(){}
    
    public static void insertPerson(Person p)
    {
        Session session=getSessionObject();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    
    public static Person getPersonById(int id)
    {
        try{
             Session session=getSessionObject();
             session.beginTransaction();
             Person p=session.get(Person.class,id);
             session.getTransaction().commit();
             session.close();
             return p;
            }catch(Exception e){
                return null;
            }
    }
    
    public static void editPersonById(Person obj,int id)
    {
            Session session=getSessionObject();
            session.beginTransaction();
            
            Person entity=session.get(Person.class,id);
            entity.setName(obj.getName());
            entity.setEmail(obj.getEmail());
            
            session.getTransaction().commit();
            session.close();
    }
    public static void deletePersonById(int id)
    {
        Session session=getSessionObject();
        session.beginTransaction();
        Person p=session.get(Person.class,id);
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }
    public static Collection<Person> getAllPerson()
    {
            Session session=getSessionObject();
            session.beginTransaction();
            Collection <Person> pList=session.createQuery("from person").getResultList();
            session.getTransaction().commit();
            session.close();
            return pList;
    }
    
    private static Session getSessionObject()
    {
        try{
            SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(entity.Person.class)
                .buildSessionFactory();
            Session session=factory.getCurrentSession();
            return session;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
}
