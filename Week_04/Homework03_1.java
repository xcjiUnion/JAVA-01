package java0.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 1.通过Future方式得到返回值
 */
public class Homework03_1 {

	public static void main(String[] args) {
		FutureTask<Integer> result = new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                return Homework03_1.sum();
            }
        });
		
		new Thread(result).start();
		try {
            System.out.println("result: " + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
	}
	
	private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
