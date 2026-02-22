package klu.skill2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Configuration config = new Configuration();
        config.configure("skill2.cfg.xml");
        
        SessionFactory factory =config.buildSessionFactory();
        Session session =factory.openSession();
        
        Transaction tx = session.beginTransaction();
        /*
        //insert
        Product p =new Product(); 
        //p.setId(9); 
        p.setName("FSAD WB"); 
        p.setDescription("y24"); 
        p.setPrice(120.0); 
        p.setQuantity(2);
        session.save(p); 
        tx.commit(); 
        System.out.println("Record Inserted Successfully"); 
         
        //Retrieve 
         
        Product p1 = session.find(Product.class,1); 
        System.out.println("Record retrieved Successfully"+ " " +p1.getName());
        
        /*
        
        //update
   
        Product p2 = session.find(Product.class,3); 
       p2.setPrice(1000); 
        p2.setQuantity(3); 
        session.update(p2); 
        Transaction tnx = session.beginTransaction(); 
        tnx.commit(); 
        System.out.println("Record Updated Successfully"); 
        
       
        
        //delete 
        Product p3 = session.find(Product.class,2); 
        session.delete(p3); 
        Transaction trnx = session.beginTransaction(); 
        trnx.commit(); 
        System.out.println("Record Deleted Successfully"); 
        */
        
        System.out.println(" \nWrite HQL queries to retrieve all products sorted by price:\n ");
        //Arranging the products in ascending order of their names
        String hqlasc = "FROM Product p ORDER BY p.price ASC";
        List<Product> proasc = session.createQuery(hqlasc,Product.class).list();
        
        for(Product pro : proasc) {
        	System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getPrice()+" "+pro.getQuantity()+" "+pro.getDescription());
        }
        System.out.println("\n");
        
        
        //Arranging the products in descending order of their names
        String hqldes = "FROM Product p ORDER BY p.price DESC";
        List<Product> prodes = session.createQuery(hqldes,Product.class).list();
        
        for(Product pro : prodes) {
        	System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getPrice()+" "+pro.getQuantity()+" "+pro.getDescription());
        }
        System.out.println("\n");
        
        
        //sorted by quantity(highest to lowest)
        
       // System.out.println("");
        System.out.println("\nWrite an HQL query to sort products by quantity (highest first).\n ");
        
        String quedes = "FROM Product p ORDER BY p.quantity DESC";
        List<Product> quandesc = session.createQuery(quedes,Product.class).list();
        
        for(Product pro : quandesc) {
        	System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getPrice()+" "+pro.getQuantity()+" "+pro.getDescription());
        }
        System.out.println("\n");
        
        
        
     // First 3 product 
        Query<Product> query1 = session.createQuery("from Product", 
        		Product.class);  
        query1.setFirstResult(0); // starting row (0 = first record)  
        query1.setMaxResults(3); // number of records to fetch  
        List<Product> firstThree = query1.list();  
        System.out.println("First 3 Employees:"); for(Product pro : firstThree)  
        {  
        System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getPrice()+" "+pro.getQuantity()+" "+pro.getDescription());  
        } 
        
        
     // Next 3 products  
        Query<Product> query2 = session.createQuery("from Product", 
        		Product.class);  
        query2.setFirstResult(3); // skip first 3 records  
        query2.setMaxResults(3); // fetch next 3 records  
        List<Product> nextThree = query2.list();  
        System.out.println("Next 3 Employees:");  
        for(Product pro : nextThree)  
        {  
        System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getPrice()+" "+pro.getQuantity()+" "+pro.getDescription());  
        } 
        
     // a. Count total number of Products
        Query<Long> q1 = session.createQuery("select count(p.quantity) from  Product p", 
        Long.class);  
        Long totalProduct = q1.uniqueResult();  
        System.out.println("Total Product: " + totalProduct); 
        
      //  b. Count products where quantity > 0 
        Query<Long> q2 = session.createQuery("select count(p.quantity)from Product p where p.quantity > 0",
                Long.class);
         Long quanCount = q2.uniqueResult();

        System.out.println("Products with quantity > 0 : " + quanCount);
        System.out.println("\n");
        
        //c. Count products grouped by description 
        Query<Object[]> q3 = session.createQuery("select p.description, count(p.description) from Product p group by p.description", Object[].class);  
        		List<Object[]> results = q3.list();  
        		System.out.println("Employee count grouped by Description:");  
        		for(Object[] row : results)  
        		{  
        		System.out.println("Description : " + row[0] + " | Count: " + row[1]);  
        		} 
        		System.out.println("\n");
        		
        //d. Find minimum and maximum price
        		Query<Object[]> q4 = session.createQuery("select min(p.price), max(p.price) from Product p", Object[].class);  
        		Object[] minMax = q4.uniqueResult(); System.out.println("Minimum Price: " 
        		+ minMax[0]);  
        		System.out.println("Maximum Price: " + minMax[1]);
        	
   //7. Write an HQL query using GROUP BY to group products by description. 
        		Query<Object[]> q = session.createQuery( "select p.description, count(p.id) from Product p group by p.description", Object[].class );  
        				List<Object[]> results1 = q.list();  
        				System.out.println("Products grouped by description:");  
        				for(Object[] row : results1)  
        				{  
        				System.out.println("Description: " + row[0] + " | Count: " + row[1]);  
        				} 
        				
        			System.out.println("\n");
        			
        //Write an HQL query using WHERE to filter products within a price range. 
        				Query<Product> qu = session.createQuery( "from Product p where p.price between 180 and 1000", Product.class );  
        						List<Product> prop = qu.list();  
        						System.out.println("\nProduct with price between 180 and 1000:");  
        						for(Product pro : prop)  
        						{  
        						System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getPrice()+" "+pro.getQuantity()+" "+pro.getDescription());  
        						}
        						System.out.println("\n");
        						
        						
        						Query<Product> q9a = session.createQuery( "from Product p where p.name like 'b%'", Product.class );  
        								List<Product> result1 = q9a.list();  
        								System.out.println("Product with name starting with 'B':");  
        								for(Product pro : result1)  
        								{  
        								System.out.println(pro.getId() + " " + pro.getName());  
        								} 
        		
        								
        								//b. Names ending with certain letters 
   Query<Product> q9b = session.createQuery( "from Product p where p.name like 'b%'", Product.class );  
   List<Product> result2 = q9b.list();  
   System.out.println("Product with name ending with 'B':");  
   for(Product pro : result2)  {  
      System.out.println(pro.getId() + " " + pro.getName());  
      	} 
   System.out.println("\n");
   
 //c. Names containing a pattern anywhere (substring) 
   Query<Product> q9c = session.createQuery( "from Product p where p.name like '%ee%'", Product.class );  
   List<Product> result3 = q9c.list();  
   System.out.println("Product with name containing 'ee':");  
   for(Product pro : result3)  
   {  
   System.out.println(pro.getId() + " " + pro.getName());  
   }
   System.out.println("\n");
        session.close();  
    }
    
    
}