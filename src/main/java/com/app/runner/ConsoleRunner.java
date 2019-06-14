package com.app.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.app.model.Product;
import com.app.repo.ProductRepository;
@Component
public class ConsoleRunner implements CommandLineRunner {
@Autowired 
private ProductRepository repo;
	@Override
	public void run(String... args) throws Exception {
	  repo.save(new Product(10,"A",3.3));
	  repo.save(new Product(11,"B",4.4));
	  repo.save(new Product(12,"C",5.5));
	  repo.save(new Product(13,"D",6.6));
	//Update
			repo.save(new Product(16,"T",10.5));
			
			//Bulk Insert
			List<Product> prod=Arrays.asList(new Product(13,"M",7.7),
											 new Product(14,"A",8.8),
											 new Product(15,"N",9.9));
			repo.saveAll(prod);
			//fetch one row
			Optional<Product> p=repo.findById(12);
			if(p.isPresent()) {
				Product pd=p.get();
				System.out.println(pd.getProdCost());
			}else { System.out.println("No Row Found");	}
			
		 //fetch all rows
	        List<Product> list=repo.findAll();
			list.forEach( System.out::println);
			
		/*	//delete
			repo.deleteById(12);
			//delete all
			repo.deleteAll();  //follow row by row deletion
			repo.deleteAllInBatch();  //delete as a bulk    */
			
		// sorting order
			repo.findAll(Sort.by(Direction.DESC,"prodId")).forEach(System.out::println);
	   //Pageable process
			PageRequest p1=PageRequest.of(2,2);
			repo.findAll(p1).forEach(System.out::println);  
	  //Example Interface(to find not null searches)
			Product p2=new Product();
			p2.setProdCost(5.5);
			Example <Product> ex=Example.of(p2);
			repo.findAll(ex).forEach(System.out::println);
	 //Example(sort)
			
			Example <Product> ex1=Example.of(p2);
			repo.findAll(Sort.by(Direction.DESC,"prodCost")).forEach(System.out::println);
 /*    //Example(pageable)
			Example <Product> ex2=Example.of(p2);
			repo.findAll(ex2,PageRequest.of(1,4)).forEach(System.out::println);   */
			
			repo.findAllById(Arrays.asList(12,15)).forEach(System.out::println);
			
	
	}

}
