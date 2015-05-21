package main;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import pojo.Event;
import pojo.Person;


public class HibernateMain {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
		@SuppressWarnings("deprecation")
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = null;
		try {
			session = factory.openSession();
			// 开启事务
			session.beginTransaction();
            Person person = new Person();
			person.setName("jack");
			person.setSn("sn001");
			person.setDescription("smart");
			Event e01 = new Event();
			e01.setName("new year");
			e01.setDescription("the birth day!");
			Event e02 = new Event();
			e02.setName("merry");
			e02.setDescription("the frist day of year !");
			Set<Event> events = new HashSet<Event>();
			events.add(e01);
			events.add(e02);
			person.setEvents(events);
			session.save(person);
			// 提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					// 关闭session
					session.close();
				}
			}
		}
	}
}
