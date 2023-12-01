package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            Pattern pattern = Pattern.compile("\\?msg=(\\w+)");
            boolean exit = false;
            while (!server.isClosed() && !exit) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String line = in.readLine();
                    Matcher matcher = pattern.matcher(line);
                    String param = matcher.find() ? matcher.group(1) : null;
                    exit = "Exit".equals(param);
                    if (!exit) {
                        String response;
                        if ("Hello".equals(param) || "What".equals(param)) {
                            response = param;
                        } else {
                            StringJoiner joiner = new StringJoiner("\r\n");
                            joiner.add(line);
                            for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                                joiner.add(str);
                            }
                            response = joiner.toString();
                        }
                        out.write(response.getBytes());
                    }
                }
            }
        }
    }
}
