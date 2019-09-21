package org.smyld.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

public class SMYLDInputStream extends BufferedInputStream implements Runnable {
	boolean stopListen = false;
	int blockSize = DEFAULT_BLOCK_SIZE;
	long sleepTime = DEFAULT_SLEEP_TIME;
	StringBuffer buffer = new StringBuffer();
	byte[] data;
	StreamListener listenerClass;
	Thread activeThread;

	public SMYLDInputStream(InputStream inputStream) {
		super(inputStream);
		data = new byte[blockSize];
	}

	public SMYLDInputStream(InputStream inputStream, int BlockSize) {
		super(inputStream, BlockSize);
		setBlockSize(BlockSize);
		data = new byte[blockSize];
	}

	public SMYLDInputStream(InputStream inputStream, int BlockSize, long SleepTime) {
		this(inputStream, BlockSize);
		sleepTime = SleepTime;
	}

	@Override
	public int read() {
		return -1;
	}

	public void run() {
		while (!stopListen) {
			int dataSize = 0;
			buffer.setLength(0);
			try {
				while ((dataSize = available()) != 0) {
					data = new byte[dataSize];
					read(data);
					// if (dataSize<blockSize){
					// byte[] lastData = new byte[dataSize];
					// System.arraycopy(data,0,lastData,0,dataSize);
					// data = lastData;
					// }
					buffer.append(new String(data));
				}
				if (buffer.length() > 0) {
					listenerClass.dataReceived(buffer.toString().getBytes());
					listenerClass.textRecieved(buffer.toString());
				}
				Thread.sleep(sleepTime);

			} catch (SocketException soc) {
				// ex.printStackTrace();
				try {
					this.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void addStreamListener(StreamListener ListenerClass) {
		listenerClass = ListenerClass;
	}

	public void stop() {
		stopListen = true;
	}

	public void resume() {
		stopListen = false;

	}

	@Override
	public void close() throws IOException {
		stop();

		super.close();
	}

	public void setBlockSize(int readingBlockSize) {
		if (readingBlockSize > 0) {
			blockSize = readingBlockSize;
			data = new byte[blockSize];
		}
	}

	public final static int DEFAULT_BLOCK_SIZE = 256;
	public final static int DEFAULT_SLEEP_TIME = 5;

}
