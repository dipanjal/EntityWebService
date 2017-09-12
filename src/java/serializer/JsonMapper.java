/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.Collection;
import entity.Person;

/**
 *
 * @author Dipanjal
 */
public class JsonMapper {
    public String serializeToJson(Person m)
    {
        String json =new Gson().toJson(m);
        return json;
    }
    public String serializeToJson(Collection<Person> mCol,String SETKEY)
    {
        JsonArray customArray=new Gson().toJsonTree(mCol).getAsJsonArray(); //CUSTOM JSON ARR
        JsonObject jsonObj=new JsonObject(); //TAKING A JSON OBJECT
        jsonObj.add(SETKEY, customArray); //ADDING JSON CUSTOM ARRAY WITH A KEY:VALUE PAIR
        String jsonStr=jsonObj.toString(); //CONVERT THAT AS JSON STRING
        return jsonStr;
    }
    public Person deserialize(String jsonStr)
    {
        Person m=new Gson().fromJson(jsonStr, Person.class);
        return m;
    }
    public Collection<Person> deserializeList(String jsonStr,String KEY)
    {
        //JsonParser parser = new JsonParser();
        JsonObject obj=new JsonParser().parse(jsonStr).getAsJsonObject(); //FIRST GETTING AS A JSON OBJECT
        JsonArray jsonArr = obj.getAsJsonArray(KEY); //RETRIVING AS JSON ARRY FROM THE OBJECT VALUE KEY
        TypeToken token=new TypeToken<Collection<Person>>(){};
        Collection<Person> mCol=new Gson().fromJson(jsonArr, token.getType()); //CONVERTING JSON ARRAY TO COLLECTION
        return mCol;
    }
}
