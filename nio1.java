import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

public class nio1 {
    static void readFully(SocketChannel sc, ByteBuffer buf) throws IOException {
        while (buf.hasRemaining()) {
            if (sc.read(buf) == -1) throw new EOFException("Server da dong ket noi");
        }
        buf.flip();
    }

    static void writeFully(SocketChannel sc, ByteBuffer buf) throws IOException {
        while (buf.hasRemaining()) sc.write(buf);
    }

    static void sendFrame(SocketChannel sc, String data) throws IOException {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buf = ByteBuffer.allocate(4 + bytes.length);
        buf.putInt(bytes.length);
        buf.put(bytes);
        buf.flip();
        writeFully(sc, buf);
    }

    static String readFrame(SocketChannel sc) throws IOException {
        ByteBuffer lenBuf = ByteBuffer.allocate(4);
        readFully(sc, lenBuf);
        int length = lenBuf.getInt();

        if (length < 0 || length > 10_000_000)
            throw new IOException("Frame length khong hop le: " + length);

        ByteBuffer dataBuf = ByteBuffer.allocate(length);
        readFully(sc, dataBuf);
        return StandardCharsets.UTF_8.decode(dataBuf).toString();
    }

    public static void main(String[] args) {
        String host = "36.50.135.242"; // Thay bang IP Server duoc cung cap
        int port = 2211;

        try (SocketChannel sc = SocketChannel.open()) {
            sc.configureBlocking(true);
            sc.socket().connect(new InetSocketAddress(host, port), 5000);
            sc.socket().setSoTimeout(5000);

            // a. Gui ma sinh vien va ma cau hoi
            sendFrame(sc, "B23DCCN171;CnnyKOEc");

            // b. Nhan dung 3 frame va ghep theo thu tu
            StringBuilder request = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                request.append(readFrame(sc));
            }

            String http = request.toString();
            System.out.println("HTTP request:\n" + http);

            // c. Trich xuat METHOD, PATH va HOST
            String[] lines = http.split("\r\n");
            String[] firstLine = lines[0].split(" ");
            String method = firstLine[0];
            String path = firstLine[1];

            String hostHeader = "";
            for (String line : lines) {
                if (line.regionMatches(true, 0, "Host:", 0, 5)) {
                    hostHeader = line.substring(5).trim();
                    break;
                }
            }

            if (hostHeader.isEmpty())
                throw new IOException("Khong tim thay header Host");

            String result = method + ";" + path + ";" + hostHeader;
            sendFrame(sc, result);

            System.out.println("Da gui: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}