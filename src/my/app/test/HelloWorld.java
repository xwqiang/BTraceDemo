package my.app.test;

import java.util.Random;

public class HelloWorld {
	public static void main(String[] args) throws Exception {
		//CaseObject object = new CaseObject();
		while (true) {
			Random random = new Random();
			execute(random.nextInt(400));
//			abc(2222);
			//object.execute(random.nextInt(4000));
		}
		
		
		
	}
	@Aop
	public static Integer execute(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
		}
		System.out.println("sleep time is=>"+sleepTime);
		return 0;
	}
	@Aop
	public static Integer abc(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
		}
		System.out.println("sleep time is=>"+sleepTime);
		return 0;
	}
}
