package ServSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class ServSocket {
	public static void main(String args[]) throws IOException {

		ServerSocket s = new ServerSocket(4444);
		Stream.generate(() -> {
			try {
				return s.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}).forEach((skt) -> {
			try {
				Handler(skt);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static void Handler(Socket skt) throws IOException {
		String data = "Connection established.";

		System.out.print("connection established.\n");
		System.out.println(new String(skt.getInputStream().readAllBytes()));
		skt.close();
	}

}
