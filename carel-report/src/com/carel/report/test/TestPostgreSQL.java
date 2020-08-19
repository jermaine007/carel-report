package com.carel.report.test;

import com.carel.report.Config;
import com.carel.report.dao.ProductInfoDao;
import com.carel.report.model.ProductInfo;


public class TestPostgreSQL {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		/*Instrumentation instrumentation = new Instrumentation();
		instrumentation.setOutputDirectory(Config.OUTPUT_DIR);
		instrumentation.instrument();*/
		Config.DB_DRIVER = "org.postgresql.Driver";
		Config.DB_URL = "jdbc:postgresql://localhost:5432/SOP";
		Config.DB_USER = "postgres";
		Config.DB_PWD = "jeef8611";
/*		Base.open(Config.DB_DRIVER, Config.DB_URL, Config.DB_USER,
				Config.DB_PWD);*/
		/*try {
			Class.forName(Config.DB_DRIVER);
			Connection connection = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PWD);
			Statement statement = connection.createStatement();
			
			String sql = "INSERT INTO product_order (completedamount, amount, productid, createddate, serialid) VALUES (1000, 1000, 'PJEZ', '20141125', '2014112501')";	
			statement.execute(sql);
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		Config.initialize();
/*		ProductOrderDao dao = new ProductOrderDao();
		ProductOrder order = new ProductOrder();
		String serialId = "2015112509";
		order.setSerialId(serialId);
		String productId = "PJEZ1";
		order.setProductId(productId);
		//int amount=1000;
		//order.setAmount(amount);
		//order.setCompletedAmount(amount);
		order.setCreatedDate(Utils.format(new Date()));
		
		//dao.insert(order);
		Product order = new Product();
		order.set(new String[]{"createddate", "amount", "productid", "completedamount", "serialid"},new Object[]{"20141125",1000,"PJEZ",1000,"2014112501"});
		order.save();
//		
//		List<Product> list = Product.findAll();
//		String json = JSONUtils.toJSON(list);
		Base.close();
		//System.err.println(json);
		List<ProductOrder> list =  dao.where("serialid=?","2015112502");
		for (ProductOrder productOrder : list) {
			System.err.println(productOrder);
		}
		dao.delete("productid=?","PJEZ1");
		//dao.update("productid=?,amount=?", "serialid=?","PJEZ",1000,serialId);
*/
		/*FailureProduct o = new FailureProduct();
		o.setAmount(1000);
		o.setCreatedDate(Utils.format(new Date()));
		o.setFailureMode("NTF INF");
		o.setFamilyName("PJEZ");
		o.setLine("PTH");
		o.setProduct("PJEZ001");
		o.setSerialId("2015112502");
		o.setOperator("admin");
		FailureProductDao dao = new FailureProductDao();
		//dao.insert(o);
		dao.delete("operator is null");*/
		
		ProductInfoDao dao = new ProductInfoDao();
		ProductInfo productInfo =  dao.first("product=?","PJEZC00000" );
		System.err.println(productInfo);
	}
}
