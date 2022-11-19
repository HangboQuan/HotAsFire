package chapter03;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author quanhangbo
 * @date 22-11-19 下午8:01
 */
public class Chapter03_B8 {

	// 在Java语言中提供了各种各样的输入/输出流Stream, 用于在不同线程之间直接传送数据
	// 一个线程发送数据到输出管道，另一个线程从输入管道中读数据
	// PipedInputStream 和 PipedOutputStream, PipedReader 和 PipedWriter

	public void writeMethod(PipedOutputStream out) {

		try {
			System.out.println("write:");
			for(int i = 0; i < 100; i ++ ) {
				String outData = "" + (i + 1);
				out.write(outData.getBytes());
				System.out.print(outData);
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B8_01 {

	public void readMethod(PipedInputStream input) {
		try {
			System.out.println("read:");
			byte[] bytes = new byte[20];
			int readLength = input.read(bytes);

			while (readLength != -1) {
				String newData = new String(bytes, 0, readLength);
				System.out.print(newData);
				readLength = input.read(bytes);
			}

			System.out.println();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B8_02_WriteData extends Thread {

	private Chapter03_B8 chapter03_b8;
	private PipedOutputStream out;

	public Chapter03_B8_02_WriteData(Chapter03_B8 chapter03_b8, PipedOutputStream out) {
		this.chapter03_b8 = chapter03_b8;
		this.out = out;
	}

	@Override
	public void run() {
		chapter03_b8.writeMethod(out);
	}
}


class Chapter03_B8_02_ReadData extends Thread {

	private Chapter03_B8_01 chapter03_b8_01;
	private PipedInputStream input;

	public Chapter03_B8_02_ReadData(Chapter03_B8_01 chapter03_b8_01, PipedInputStream input) {
		this.chapter03_b8_01 = chapter03_b8_01;
		this.input = input;
	}

	@Override
	public void run() {
		chapter03_b8_01.readMethod(input);
	}
}

class Chapter03_B8_03 {

	public static void main(String[] args) {


		/**
		 * 先写后读：
		 * write:
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 * read:
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 *
		 *
		 * 先读后写(先读的时候，当时没有数据写入，线程阻塞在input.read(bytes), 知道有数据被写入，才继续向下运行)：
		 * read:
		 * write:
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 */
		try {
			Chapter03_B8 chapter03_b8 = new Chapter03_B8();
			Chapter03_B8_01 chapter03_b8_01 = new Chapter03_B8_01();

			PipedInputStream inputStream = new PipedInputStream();
			PipedOutputStream outputStream = new PipedOutputStream();


			// 作用是：使两个Stream之间产生通信链接，这样才可以将数据进行输入和输出
			outputStream.connect(inputStream);


			Chapter03_B8_02_ReadData chapter03_b8_02_readData = new Chapter03_B8_02_ReadData(chapter03_b8_01, inputStream);
			chapter03_b8_02_readData.start();

			Thread.sleep(2000);
			Chapter03_B8_02_WriteData chapter03_b8_02_writeData = new Chapter03_B8_02_WriteData(chapter03_b8, outputStream);
			chapter03_b8_02_writeData.start();



		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}