package laofuzi.java.core.runtime;

/**
 * eclipse里的terminate是直接kill进程，所以hook不会执行
 *
 */
public class ShutdownHook {
	public static void main(String[] args) throws InterruptedException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("shutdown hook executed");
			}
		});
		System.out.println("shutdown hook registered");
		Thread.sleep(1000*10);
		System.exit(0);
	}
}
