package com.yc.dao;

import java.util.List;
/**
 * ���ݿ���������ӿ� crud
 * @author hp
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * ��Ӳ���
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int add(T t)throws Exception;
	
	/**
	 * ����������ѯ
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<T> findByTrem(T t)throws Exception;
	
	/**
	 * �޸Ĳ���
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int update(T t)throws Exception;
	
	/**ɾ������  ����ɾ��  �߼�ɾ��
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int delete(T t)throws Exception;
}
