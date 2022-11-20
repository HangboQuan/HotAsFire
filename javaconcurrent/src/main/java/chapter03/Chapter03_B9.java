package chapter03;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author quanhangbo
 * @date 22-11-20 下午7:55
 */
public class Chapter03_B9 {

	public void writeMethod(PipedWriter out) {
		try {
			System.out.println("write:");
			for(int i = 0; i < 100; i ++ ) {
				String outData = "" + (i + 1);
				out.write(outData);
				System.out.print(outData);
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B9_01 {

	public void readMethod(PipedReader reader) {
		try {
			System.out.println("read ");
			char[] charArray = new char[20];
			int readLength = reader.read(charArray);
			while (readLength != -1) {
				String newData = new String(charArray, 0, readLength);
				System.out.print(newData);
				readLength = reader.read(charArray);
			}
			System.out.println();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B9_02 extends Thread {

	private Chapter03_B9 chapter03_b9;
	private PipedWriter out;

	public Chapter03_B9_02(Chapter03_B9 chapter03_b9, PipedWriter out) {
		this.chapter03_b9 = chapter03_b9;
		this.out = out;
	}

	@Override
	public void run() {
		chapter03_b9.writeMethod(out);
	}
}


class Chapter03_B9_03 extends Thread {
	private Chapter03_B9_01 chapter03_b9_01;
	private PipedReader reader;

	public Chapter03_B9_03(Chapter03_B9_01 chapter03_b9_01, PipedReader reader) {
		this.chapter03_b9_01 = chapter03_b9_01;
		this.reader = reader;
	}

	@Override
	public void run() {
		chapter03_b9_01.readMethod(reader);
	}
}


class Chapter03_B9_04 {


	/**
	 * 以字符串的方式 管道进行线程间通信
	 * write:
	 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
	 * read
	 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter03_B9 chapter03_b9 = new Chapter03_B9();
			Chapter03_B9_01 chapter03_b9_01 = new Chapter03_B9_01();

			PipedWriter writer = new PipedWriter();
			PipedReader reader = new PipedReader();

			writer.connect(reader);

			Chapter03_B9_02 chapter03_b9_02 = new Chapter03_B9_02(chapter03_b9, writer);
			Chapter03_B9_03 chapter03_b9_03 = new Chapter03_B9_03(chapter03_b9_01, reader);

			chapter03_b9_02.start();
			Thread.sleep(1000);
			chapter03_b9_03.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}