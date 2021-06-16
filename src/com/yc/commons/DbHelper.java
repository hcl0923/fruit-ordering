package com.yc.commons;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper {
	private Connection conn;// ���Ӷ���
	private PreparedStatement pstmt;// Ԥ�������
	private ResultSet rs;// �����
	// ����ڵ�һ����ʱִ��

	static {
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ���Ӷ���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException {
		// conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");
		conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"), MyProperties.getInstance());// �Զ���MyProperties.getInstance()�е�����
		return conn;
	}
	
	// �ر�������Դ
	public void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			// �رս����
			if (null != rs) {
				rs.close();
			}
			// �رձ������
			if (null != stmt) {
				stmt.close();
			}
			// �ر����Ӷ���
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����sql���ĸ��£�������  ��ѯ�ϸ�����list
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int update(String sql,Object...params)throws Exception{
		int result=0;
		try {
			//��ȡ���Ӷ���
			conn=this.getConn();
			//��ȡԤ�������
			pstmt=conn.prepareStatement(sql);
			//���ò���
			//����
			if(null!=params) {
				for(int i=0;i<params.length;i++) {
					pstmt.setObject(i+1, params[i]);//���ò���
				}
			}
			result=pstmt.executeUpdate();
		}finally {
			this.closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	public <T> List<T> findMutipl(String sql,List<Object> params,Class<T> cls)throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{
		List<T> list=new ArrayList<T>();
		T t;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs=pstmt.executeQuery();
			//��ȡ���еķ������ֶ���
			Method []methods=cls.getDeclaredMethods();
			Field []fields=cls.getDeclaredFields();
			while(rs.next()) {
				//��������  ���ݷ��䴴��
				t=cls.newInstance();
				for(Method m:methods) {
					for(Field f:fields) {
						String fieldName=f.getName();
						//set���ֶ���ƴ��  ��ΪsetName  �뷽�������бȽ�
						if(("set"+fieldName).equalsIgnoreCase(m.getName())) {
							String type=m.getParameterTypes()[0].getName();
							if("java.lang.Integer".equals(type)) {
								m.invoke(t, rs.getInt(fieldName));//���ݿ���ֶ���Ҫ�Ͷ�������fieldһ��
							}else if("java.lang.Double".equals(type)){
								m.invoke(t, rs.getDouble(fieldName));
							}else if("java.lang.Float".equals(type)){
								m.invoke(t, rs.getFloat(fieldName));
							}else if("java.lang.Long".equals(type)){
								m.invoke(t, rs.getLong(fieldName));
							}else if("java.lang.String".equals(type)){
								m.invoke(t, rs.getString(fieldName));
							}else {
								//������չ
							}
						}
					}
				}
				list.add(t);//������ӵ�list������ȥ
			}
		}finally {
			closeAll(conn, pstmt, rs);
		}
		
		return list;
	}
	/**
	 * ��ѯһ����¼  ��װ��javaBean������
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> T find(String sql,List<Object> params,Class<T> cls) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException{//����   class����
		T t=null;//tʵ��
		
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs=pstmt.executeQuery();
			Method []methods=cls.getDeclaredMethods();
			Field []fields=cls.getDeclaredFields();
			if(rs.next()) {
				//��������  ���ݷ��䴴��
				t=cls.newInstance();
				for(Method m:methods) {
					for(Field f:fields) {
						String fieldName=f.getName();
						//set���ֶ���ƴ��  ��ΪsetName  �뷽�������бȽ�
						if(("set"+fieldName).equalsIgnoreCase(m.getName())) {
							String type=m.getParameterTypes()[0].getName();
							if("java.lang.Integer".equals(type)) {
								m.invoke(t, rs.getInt(fieldName));//���ݿ���ֶ���Ҫ�Ͷ�������fieldһ��
							}else if("java.lang.Double".equals(type)){
								m.invoke(t, rs.getDouble(fieldName));
							}else if("java.lang.Float".equals(type)){
								m.invoke(t, rs.getFloat(fieldName));
							}else if("java.lang.Long".equals(type)){
								m.invoke(t, rs.getLong(fieldName));
							}else if("java.lang.String".equals(type)){
								m.invoke(t, rs.getString(fieldName));
							}
						}
					}
				}
			}
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return t;
	}

	/**
	 * ����sql�����²���
	 * 
	 * @param sql    sql���
	 * @param params �������� �������˳�����ͣ�˳��һ��
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, List<Object> params) throws SQLException {
		int result = 0;
		try {
			// ��ȡ���Ӷ���
			conn = this.getConn();
			// ��ȡԤ�������
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			setParams(pstmt, params);
			result = pstmt.executeUpdate();
		} finally {
			this.closeAll(conn, pstmt, null);
		}
		return result;
	}

	/**
	 * ���ò���
	 * 
	 * @param pstmt2 Ԥ�������
	 * @param params ��������
	 */
	private void setParams(PreparedStatement pstmt, List<Object> params) throws SQLException {
		if (null == params || params.size() <= 0) {
			return;
		}
		for (int i = 0; i < params.size(); i++) {
			pstmt.setObject(i + 1, params.get(i));// �ʺ��������Ǵ�1��ʼ��
		}
	}

	/**
	 * ��ѯ���� select * from tb_name where id = ? ����һ����¼
	 * 
	 * @param sql    sql���
	 * @param params ����
	 * @return
	 * @throws Exception
	 */
//	public Map<String, Object> findSingle(String sql, List<Object> params) throws Exception {
//		Map<String,Object> map=null;
//		try{
//			//��ȡ���Ӷ���
//			conn=getConn();
//			//��ȡ�������
//			pstmt=conn.prepareStatement(sql);
//			//���ò���
//			setParams(pstmt,params);
//			//ִ�в�ѯ���������ؽ����
//			rs=pstmt.executeQuery();
//			//��ȡ���е�����
//			List<String> columnNames=getColumnNames(rs);
//			
//			if(rs.next()){
//				//����Map����
//				map=new HashMap<String,Object>();
//				//map.put("",rs.getObject(""));
//				//ѭ������
//				for(String name:columnNames){
//					//��ȡ��ֵ
//					Object obj=rs.getObject(name);
//					if(obj==null){
//						continue;//ִ����һ�ε�ѭ��
//					}
//					String typeName=obj.getClass().getName();//������
//					//System.out.println(typeName);
//					if("oracle.sql.BLOB".equals(typeName)){
//						//ͼƬ  ���ֽ��������ʽ�洢��Map��
//						BLOB blob=(BLOB) rs.getBlob(name);
//						InputStream in=blob.getBinaryStream();
//						byte[] bt=new byte[(int) blob.length()];
//						in.read(bt);
//						in.close();
//						map.put(name, bt);
//					}else if("oracle.sql.CLOB".equals(typeName)){
//						CLOB clob=(CLOB)rs.getClob(name);
//						Reader rd=clob.getCharacterStream();
//						char[] cs=new char[(int)clob.length()];
//						rd.read(cs);
//						String str=new String(cs);
//						rs.close();
//						map.put(name, str);
//					}else{
//						map.put(name, rs.getObject(name));
//					}
//				}
//			}
//		}finally{
//			this.closeAll(conn, pstmt, rs);
//		}
//		return map;
//	}
	/**
	 * ���ݽ������ȡ���е�����
	 * 
	 * @param rs2
	 * @return
	 * @throws SQLException
	 */
	private List<String> getColumnNames(ResultSet rs) throws SQLException {
		List<String> columnNames = new ArrayList<String>();
		ResultSetMetaData data = rs.getMetaData();// ������
		int count = data.getColumnCount();
		// ��ȡ����
		for (int i = 1; i <= count; i++) {
			columnNames.add(data.getColumnName(i));
		}
		return columnNames;
	}

	/**
	 * ���ض�����¼
	 * 
	 * @param sql
	 * @param params
	 * @return ���ض�������
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<Map<String, Object>> findMutiple(String sql, List<Object> params) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			// ��ȡ���Ӷ���
			conn = getConn();
			// ��ȡ�������
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			setParams(pstmt, params);
			// ִ�в�ѯ���������ؽ����
			rs = pstmt.executeQuery();
			// ��ȡ���е�����
			List<String> columnNames = getColumnNames(rs);
			// System.out.println(columnNames.size());
			while (rs.next()) {
				// ����Map����
				map = new HashMap<String, Object>();
				// map.put("",rs.getObject(""));
				// ѭ������
				for (String name : columnNames) {
					// ��ȡ��ֵ
					Object obj = rs.getObject(name);
					if (obj == null) {
						continue;// ִ����һ�ε�ѭ��
					}
					String typeName = obj.getClass().getName();
					// System.out.println(typeName);
					if ("oracle.sql.BLOB".equals(typeName)) {
//						//ͼƬ  ���ֽ��������ʽ�洢��Map��
//						BLOB blob=(BLOB) rs.getBlob(name);
//						InputStream in=blob.getBinaryStream();
//						byte[] bt=new byte[(int) blob.length()];
//						in.read(bt);
//						in.close();
//						map.put(name, bt);
					} else if ("oracle.sql.CLOB".equals(typeName)) {
//						CLOB clob=(CLOB)rs.getClob(name);
//						Reader rd=clob.getCharacterStream();
//						char[] cs=new char[(int)clob.length()];
//						rd.read(cs);
//						String str=new String(cs);
//						rd.close();
//						map.put(name, str);
					} else {
						map.put(name, rs.getObject(name));
					}
				}
				// ���List������
				list.add(map);
			}
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * ����update����������
	 * 
	 * @param sqls
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int update(List<String> sqls, List<List<Object>> params) throws SQLException {
		int result = 0;
		try {
			conn = getConn();
			// �鿴API conn ģʽ�Զ��ύ ��������Ϊ�ֶ��ύ
			conn.setAutoCommit(false);
			// ѭ��sql��䣬�Լ�sql����Ӧ�Ĳ���list����
			for (int i = 0; i < sqls.size(); i++) {
				// ͨ��ָ����sql��� ���Ӷ����ȡԤ�������
				pstmt = conn.prepareStatement(sqls.get(i));// ��ȡsql����Ӧ�Ĳ�������
				// ���ò���
				List<Object> param = params.get(i);
				setParams(pstmt, param);
				result = pstmt.executeUpdate();
				if (result <= 0) {
					conn.rollback();// ����ع�
					return result;
				}
			}
			// �����ύ
			conn.commit();
		} catch (Exception e) {
			conn.rollback();// ����ع�
			e.printStackTrace();
		} finally {
			// ��ԭ�����״̬
			conn.rollback();
			closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	/**
	 * �ۺϺ�����ѯ  ����һ�е�ֵ sum avg count max min
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public double getPolymer(String sql,List<Object>params) throws SQLException {
		double result=0;
		try {
			// ��ȡ���Ӷ���
			conn = getConn();
			// ��ȡ�������
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			setParams(pstmt, params);
			// ִ�в�ѯ���������ؽ����
			rs = pstmt.executeQuery();
			// ��ȡ���е�����
			List<String> columnNames = getColumnNames(rs);
			if (rs.next()) {
				result=rs.getDouble(1);//ֻ��һ��
			}
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
//	public static void main(String[] args) throws SQLException {
//		DbHelper db=new DbHelper();
//		System.out.println(db.getConn().getClass().getName());
//	}
}
