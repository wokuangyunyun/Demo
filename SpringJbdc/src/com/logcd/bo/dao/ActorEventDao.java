package com.logcd.bo.dao;

import java.util.List;

import org.springframework.jdbc.support.KeyHolder;

import com.logcd.bo.Actor;

public interface ActorEventDao {
	/**
	 * ����SQL����
	 * 
	 * @param sql
	 */
	public void createTableBySQL(String sql);

	/**
	 * ͳ��firstName��ͬ������
	 * 
	 * @param firstName
	 * @return
	 */
	public int findCountOfActorsByFirstName(String firstName);

	/**
	 * �����¼�������Զ����ɵ�����Id
	 * 
	 * @param ps
	 * @return
	 */
	public KeyHolder insertActor(final Actor actor);

	/**
	 * ��SimpleJdbcInsert����һ����¼:mysql���Գɹ�
	 */
	public long inserOneActor(Actor actor);

	/**
	 * ����/����/ɾ������
	 * 
	 * @param sql
	 *            �в������
	 * @param obj
	 *            ����ֵ����
	 */
	public int operateActor(String sql, Object[] obj);

	/**
	 * ����SQL��ѯ��¼����
	 * 
	 * @param sql
	 * @return
	 */
	public int findRowCountBySQL(String sql);

	/**
	 * ����Id����ָ������
	 * 
	 * @param id
	 * @return
	 */
	public Actor findActorById(long id);

	/**
	 * ����Id����ָ������
	 * 
	 * @param id
	 * @return
	 */
	public Actor findActorByIdSimple(long id);

	/**
	 * �������ж���
	 * 
	 * @return
	 */
	public List findAllActors();

	/**
	 * ��������
	 * 
	 * @param actors
	 * @return
	 */
	public int[] updateBatchActors(final List actors);

	/**
	 * ��������
	 * 
	 * @param actors
	 * @return
	 */
	public int[] updateBatchActorsSimple(final List<Actor> actors);

}
