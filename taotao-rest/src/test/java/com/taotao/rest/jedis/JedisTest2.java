package com.taotao.rest.jedis;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

public class JedisTest2 {

	//jedis使用事物方法
		@Test
		public void testJedisMy() {
			
			//创建一个jedis的对象。
			Jedis jedis = new Jedis("192.168.247.128", 6379);
			
			//调用jedis对象的方法，方法名称和redis的命令一致。
			Transaction transaction = jedis.multi();
			transaction.set("key1", "jedis wangyy-key1");
			transaction.exec();
			String string = jedis.get("key111");
			System.out.println(string);
			//关闭jedis。
			jedis.close();
		}
		
		//--------------------------------批量执行的几种不同方法------------------------------------------------------------
		
		//一、普通同步方式 
		//最简单和基础的调用方式， 

		@Test 
		public void test1Normal() { 
		    Jedis jedis = new Jedis("192.168.247.128", 6379); 
		    long start = System.currentTimeMillis(); 
		    for (int i = 0; i < 100000; i++) { 
		        String result = jedis.set("n" + i, "n" + i); 
		    } 
		    long end = System.currentTimeMillis(); 
		    System.out.println("Simple SET: " + ((end - start)/1000.0) + " seconds"); 
		    jedis.disconnect(); 
		} 

		//很简单吧，每次set之后都可以返回结果，标记是否成功。 
		
		
		//二、事务方式(Transactions)
		//redis的事务很简单，他主要目的是保障，一个client发起的事务中的命令可以连续的执行，而中间不会插入其他client的命令。 

		@Test 
		public void test2Trans() { 
		    Jedis jedis = new Jedis("192.168.247.128", 6379); 
		    long start = System.currentTimeMillis(); 
		    Transaction tx = jedis.multi(); 
		    for (int i = 0; i < 100000; i++) { 
		        tx.set("t" + i, "t" + i); 
		    } 
		    List<Object> results = tx.exec(); 
		    long end = System.currentTimeMillis(); 
		    System.out.println("Transaction SET: " + ((end - start)/1000.0) + " seconds"); 
		    jedis.disconnect(); 
		} 

		//我们调用jedis.watch(…)方法来监控key，如果调用后key值发生变化，则整个事务会执行失败。另外，事务中某个操作失败，并不会回滚其他操作。这一点需要注意。还有，我们可以使用discard()方法来取消事务。 
		
		
		//三、管道(Pipelining) 
		//有时，我们需要采用异步方式，一次发送多个指令，不同步等待其返回结果。这样可以取得非常好的执行效率。这就是管道，调用方法如下： 

		@Test 
		public void test3Pipelined() { 
		    Jedis jedis = new Jedis("192.168.247.128", 6379); 
		    Pipeline pipeline = jedis.pipelined(); 
		    long start = System.currentTimeMillis(); 
		    for (int i = 0; i < 100000; i++) { 
		        pipeline.set("p" + i, "p" + i); 
		    } 
		    List<Object> results = pipeline.syncAndReturnAll(); 
		    long end = System.currentTimeMillis(); 
		    System.out.println("Pipelined SET: " + ((end - start)/1000.0) + " seconds"); 
		    jedis.disconnect(); 
		} 

		//四、管道中调用事务 

		//就Jedis提供的方法而言，是可以做到在管道中使用事务，其代码如下： 

		@Test 
		public void test4combPipelineTrans() { 
		    Jedis jedis= new Jedis("192.168.247.128", 6379); 
		    long start = System.currentTimeMillis(); 
		    Pipeline pipeline = jedis.pipelined(); 
		    pipeline.multi(); 
		    for (int i = 0; i < 100000; i++) { 
		        pipeline.set("" + i, "" + i); 
		    } 
		    pipeline.exec(); 
		    List<Object> results = pipeline.syncAndReturnAll(); 
		    long end = System.currentTimeMillis(); 
		    System.out.println("Pipelined transaction: " + ((end - start)/1000.0) + " seconds"); 
		    jedis.disconnect(); 
		} 
		
		//五  略
		
		//六 略
		
		

}
