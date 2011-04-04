package ganzhi.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TestNIO2 {
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {

		Path home = FileSystems.getDefault().getPath("/home/ganzhi");
		Path profile = home.resolve(".bashrc");
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(profile,
				StandardOpenOption.READ);
		ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
		channel.read(buffer, 0, buffer,
				new CompletionHandler<Integer, ByteBuffer>() {
					@Override
					public void completed(Integer result, ByteBuffer attachment) {
						System.out.println("Read completed" + result + " "
								+ attachment);
					}

					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
						System.err.println("Read failed");
					}
				});
		Thread.sleep(10);
	}
}
