package klu.skill2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
        
        //update
   
        Product p2 = session.find(Product.class,2); 
        p2.setPrice(168); 
        p2.setQuantity(5); 
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
        
        session.close();  
    }
}
