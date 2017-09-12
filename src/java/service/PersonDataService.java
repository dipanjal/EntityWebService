/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import dao.PersonDao;
import entity.Person;
import java.util.ArrayList;
import java.util.Collection;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import serializer.JsonMapper;

/**
 *
 * @author Dipanjal
 */
@WebService(serviceName = "PersonDataService")
public class PersonDataService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertPerson")
    public void insertPerson(@WebParam(name = "jsonStr") String jsonStr) {
        //TODO write your implementation code here:
        try{
            Person obj=new Gson().fromJson(jsonStr, Person.class);
            PersonDao.insertPerson(obj);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPersonById")
    public String getPersonById(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        Person p=PersonDao.getPersonById(id);
        String jsonStr=new Gson().toJson(p);
        return jsonStr;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editPersonEntity")
    public void editPersonEntity(@WebParam(name = "jsonStr") String jsonStr, 
            @WebParam(name = "id") int id) 
    {
        Person obj=new Gson().fromJson(jsonStr, Person.class);
        PersonDao.editPersonById(obj, id);

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deletePersonById")
    public void deletePersonById(@WebParam(name = "id") int id) {
        PersonDao.deletePersonById(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllPerson")
    public String getAllPerson() 
    {
        Collection<Person> pCol=PersonDao.getAllPerson();
        //Collection<Person> pCol=new ArrayList<Person>();
//        for(int i=0;i<5;i++)
//        {
//            pCol.add(new Person("E-"+i,"test"+i+"@email.com"));
//        }
        String jsonStr=new JsonMapper().serializeToJson(pCol, "personCollection");
        return jsonStr;
        //return null;
    }
}
