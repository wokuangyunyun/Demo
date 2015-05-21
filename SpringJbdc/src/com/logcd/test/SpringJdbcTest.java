package com.logcd.test;   
  
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;   
import org.springframework.context.support.ClassPathXmlApplicationContext;   
import org.springframework.jdbc.support.KeyHolder;   
  
import com.logcd.bo.Actor;   
import com.logcd.bo.dao.ActorEventDao;   
  
import junit.framework.TestCase;   
  
/**  
 * @author logcd  
 */  
public class SpringJdbcTest extends TestCase {   
  
    private ActorEventDao actorEventDao;     
       
    protected void setUp() throws Exception {   
        super.setUp();   
        ApplicationContext context = new ClassPathXmlApplicationContext("springJdbcContext.xml");   
        actorEventDao = (ActorEventDao)context.getBean("actorEventDao");    
    }   
  
    protected void tearDown() throws Exception {   
        super.tearDown();   
    }   
  
    public void testActorEventDao(){   
        String creatSql = "create table ACTORS(" +   
        "ID int not null auto_increment," +   
        "FIRST_NAME varchar(15)," +   
        "LAST_NAME varchar(15)," +   
        "primary key (ID)" +   
        ");" ;   
        //建表   
        actorEventDao.createTableBySQL(creatSql);   
           
        String addSql = "insert into actors(first_name,last_name) values(?,?);";   
        Object[] obj = new Object[]{"wang","jinming"};   
        //新增   
        System.out.println(actorEventDao.operateActor(addSql, obj));   
       
        String countSql = "select count(0) from actors";   
        System.out.println("Count:"+actorEventDao.findRowCountBySQL(countSql));     
        //根据id查找   
        Actor actor = actorEventDao.findActorById(1);   
        System.out.println("id:"+actor.getId()+"  first_name:"+actor.getFirstName()+"  last_name:"+actor.getLastName());   
        //输出所有   
        for(Object o:actorEventDao.findAllActors()){   
            Actor act = (Actor) o;   
            System.out.println("id:"+act.getId()+"  first_name:"+act.getFirstName()+"  last_name:"+act.getLastName());   
        }   
           
        Actor newAct=new Actor();   
        newAct.setFirstName("jin");   
        newAct.setLastName("ming");   
        KeyHolder keyHold =actorEventDao.insertActor(newAct);   
        System.out.println(keyHold.getKey());//mysql得不到id   
  
        List<Actor> list = new ArrayList<Actor>();   
        for(Object o:actorEventDao.findAllActors()){   
            Actor act = (Actor) o;   
            System.out.println("id:"+act.getId()+"  first_name:"+act.getFirstName()+"  last_name:"+act.getLastName());   
            act.setLastName("www");   
            list.add(act);   
        }    
        for(Object o:actorEventDao.findAllActors()){   
            Actor act = (Actor) o;   
            System.out.println("id:"+act.getId()+"  first_name:"+act.getFirstName()+"  last_name:"+act.getLastName());   
        }   
    }   
}
