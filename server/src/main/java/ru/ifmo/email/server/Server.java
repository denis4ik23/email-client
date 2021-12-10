package ru.ifmo.email.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


    public class Server extends Thread {
        //private ServerSocket ssocket;
        ///
        private class ConnectionServer extends Thread implements AutoCloseable {
            private final Socket socket;

            private ConnectionServer(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try (ServerSocket ssocket = new ServerSocket(port)) {
                    this.ssocket = ssocket;

                    while (!isInterrupted()) {
                        final Socket socket = ssocket.accept();

                        final ConnectionServer connectionServer = new ConnectionServer(socket);
                        synchronized (connections) {
                            connections.add(connectionServer);
                        }
                        connectionServer.start();
                    }
                } catch (IOException e) {
                    // Можем пропустить вывод ошибки в консоль, если мы знаем, что это был останов.
                    if (!isInterrupted()) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void close() throws Exception {
                interrupt();
                // Аналогично с ServerSocket, метод read не завершится по вызову interrupt().
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }
