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
            // 注册 JDBC 驱动
            Class.forName(set.JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(set.DB_URL,set.USER,set.PASS);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = getSQL();
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            ResultSetMetaData md = rs.getMetaData();

            int columnCount = md.getColumnCount();
            
            while(rs.next()){
            	
            	Map<String, Object> data = new HashMap<String, Object>();
            	
                for (int i = 1; i <= columnCount; i++) {

                    data.put(md.getColumnName(i), rs.getObject(i));

                }
                
                dataList.add(data);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
		return dataList;
	}   
}
