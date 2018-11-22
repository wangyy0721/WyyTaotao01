package com.taotao.rest.redisLock;

public class Test {
	
	public static void main(String[] args) {
        final Service service = new Service();
        for (int i = 0; i < 50; i++) {
            Thread threadA = new Thread(new Runnable() {
				
				@Override
				public void run() {
					service.seckill();
				}
			});
            threadA.start();
        }
    }

}
