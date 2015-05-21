package demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.PlanOrder;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PlanOrder order = new PlanOrder(); // person为new状态
		order.setSn("p-01920312");
		order.setProductName("PC");
		em.persist(order); // 持久化实体
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
