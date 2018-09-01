package my_app;

import java.util.List;
import java.util.Map;

import my_app.mySql;

public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    mySql mySql = new mySql();
	    mySql.setSQL("SELECT id, title, `from`, main, content, time FROM my_adarticle");
	    List<Map<String, Object>> data = mySql.getData();
	    System.out.print(data);
	    
	}

}
