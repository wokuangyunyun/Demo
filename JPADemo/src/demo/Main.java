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
		PlanOrder order = new PlanOrder(); // personΪnew״̬
		order.setSn("p-01920312");
		order.setProductName("PC");
		em.persist(order); // �־û�ʵ��
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
