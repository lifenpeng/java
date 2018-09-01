package my_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import my_app.Setting;


public class mySql {
	
	Setting set = new Setting();
	
    private String SQL = null;
    
	public String getSQL() {
		return SQL;
	}
	public void setSQL(String sQL) {
		SQL = sQL;
	}
	
	public List<Map<String, Object>> getData() {
		Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        
        try{
            // ע�� JDBC ����
            Class.forName(set.JDBC_DRIVER);
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(set.DB_URL,set.USER,set.PASS);
        
            // ִ�в�ѯ
            stmt = conn.createStatement();
            String sql;
            sql = getSQL();
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            ResultSetMetaData md = rs.getMetaData();

            int columnCount = md.getColumnCount();
            
            while(rs.next()){
            	
            	Map<String, Object> data = new HashMap<String, Object>();
            	
                for (int i = 1; i <= columnCount; i++) {

                    data.put(md.getColumnName(i), rs.getObject(i));

                }
                
                dataList.add(data);
            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
		return dataList;
	}   
}
