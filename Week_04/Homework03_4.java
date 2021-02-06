package java0.conc0303;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池， 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 4.通过多线程锁方式得到返回值
 */
public class Homework03_4 {

	public static void main(String[] args) {
		MethodClass methodClass = new MethodClass();
		Thread t1 = new Thread(() -> {
			 methodClass.produceSum();
		}, "t1");
		
		Thread t2 = new Thread(() -> {
			System.out.println(methodClass.getSum());
        }, "t2");
		
		t1.start();
		t2.start();
	}

}

class MethodClass {
	
	private int sum = 0;
	
	public synchronized void produceSum() {
		sum = fibo(36);
    }
    
    private int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
    
    public synchronized int getSum() {
    	return sum;
    }
	
}
