package practice;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.*;

/**
 * @author quanhangbo
 * @date 23-5-18 下午4:55
 */
public class CountDownLatchV1 {

	public static ExecutorService executors = Executors.newFixedThreadPool(5);
	public static CountDownLatch countDownLatch = new CountDownLatch(3);

	public IntentResponse parallelTopicExactMatch() {
		Callable<IntentResponse> A1 = () -> {
			try {
				Thread.sleep(10000);
				countDownLatch.countDown();
				return new IntentResponse(0.948f, "xiaoAi", "personaXiaoAi");
			} catch (Exception e) {

			}
			return null;
		};
		Callable<IntentResponse> A2 = () -> {
			try {
				Thread.sleep(200);
				countDownLatch.countDown();
				return new IntentResponse(1.0f, "control", "intervene");
			} catch (Exception e) {

			}
			return null;
		};
		Callable<IntentResponse> A3 = () -> {
			try {
				Thread.sleep(800);
				countDownLatch.countDown();
				return new IntentResponse(1.0f, "control", "intervene");
			} catch (Exception e) {

			}
			return null;
		};

		Future<IntentResponse> A1Future = executors.submit(A1);
		Future<IntentResponse> A2Future = executors.submit(A2);
		Future<IntentResponse> A3Future = executors.submit(A3);

		try {
			IntentResponse response1 = A1Future.get();
			IntentResponse response2 = A2Future.get();
			IntentResponse response3 = A3Future.get();
			System.out.println(response1);
			System.out.println(response2);
			System.out.println(response3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Data
	@Accessors
	class IntentResponse {
		float confidence;
		String subtype;
		String entity;

		public IntentResponse(float confidence, String subtype, String entity) {
			this.confidence = confidence;
			this.subtype = subtype;
			this.entity = entity;
		}

		public IntentResponse() {

		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new CountDownLatchV1().parallelTopicExactMatch();
		System.out.println(System.currentTimeMillis() - start);
	}

}
