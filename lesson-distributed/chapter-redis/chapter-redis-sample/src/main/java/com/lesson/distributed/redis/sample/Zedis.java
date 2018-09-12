package com.lesson.distributed.redis.sample;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 *   自写 jedis
 *
 *   resp 协议
 *
 *
 *
 *
 * @author zhengshijun
 * @version created on 2018/9/12.
 */
public class Zedis {

    private static final Logger log = LoggerFactory.getLogger(Zedis.class);
    private Socket socket;
    private String ip;
    private int port;
    private String auth;
    private final static String RN = StringUtils.CR.concat(StringUtils.LF);

    private byte[] bytes = null;

    public Zedis(String ip, int port, String auth) {
        this.ip = ip;
        this.port = port;
        this.auth = auth;
        try {
            socket = new Socket(ip, port);
        } catch (Exception e) {
            log.error(StringUtils.EMPTY, e);
        }
        init();
    }

    private OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    private InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    private void init() {

        try {
            sendCommand("AUTH",auth);

            String result = response();

            log.info("result:{}",result);
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);
        }


    }

    public String set(String key, String value) {


        try {
            sendCommand("SET",key,value);

            String result =  response();
            log.info("set result:{}",result);
            return result;
        } catch (Exception e) {
            log.error(StringUtils.EMPTY);
        }
        return null;

    }
    public String get(String key) {
        try {
            sendCommand("GET",key);

            String result =  response();
            log.info("get result:{}",result);
            return result;
        } catch (Exception e) {
            log.error(StringUtils.EMPTY);
        }
        return null;

    }

    /**
     * 发送命令
     * @param command
     * @param args
     * @throws IOException
     */
    public void sendCommand(String command,String...args) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        // *多少组数据
        stringBuilder.append("*").append(args.length+1).append(RN);
        stringBuilder.append("$").append(command.getBytes().length).append(RN);
        stringBuilder.append(command).append(RN);
        for (String arg : args) {
            stringBuilder.append("$").append(arg.getBytes().length).append(RN);
            stringBuilder.append(arg).append(RN);
        }
        log.info(StringUtils.LF+"command: \n{}",stringBuilder.toString());
        OutputStream outputStream = getOutputStream();
        outputStream.write(stringBuilder.toString().getBytes());
        outputStream.flush();
    }

    public String response() throws IOException{
        InputStream inputStream = getInputStream();
        byte[] response = new byte[2048];
        int count = inputStream.read(response);
        if (count < 0){
            return null;
        }
        byte flag = response[0];
        bytes = new byte[count];


        System.arraycopy(response, 0, bytes, 0, count);

        switch (flag){
            case '+':

                break;
            case '$':

                break;
        }


        return new String(bytes);
    }

    public String mdel(String... keys) {

        try {
            sendCommand("DEL",keys);

            String result =  response();
            log.info("mdel result:{}",result);
        } catch (Exception e) {
            log.error(StringUtils.EMPTY);
        }
        return null;

    }

    public boolean isConnected() {
        return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected()
                && !socket.isInputShutdown() && !socket.isOutputShutdown();
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            log.error(StringUtils.EMPTY, e);
        }
    }
}
