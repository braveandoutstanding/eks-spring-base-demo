package com.eks.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JschUtils {
    private final static int DEFAULT_PORT = 22;
    private final static int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;
    private final static String DEFAULT_CHARSETNAME = "UTF-8";
    public static String shell(String username,String host,String password,String script) throws Exception {
        return JschUtils.shell(username,host,DEFAULT_PORT,password,script,DEFAULT_CONNECT_TIMEOUT,DEFAULT_CHARSETNAME,DEFAULT_CHARSETNAME);
    }
    public static String shell(String username,String host,int port,String password,String script,int connectTimeout,String writeCharsetName,String readerCharsetName) throws Exception {
        JSch jsch = new JSch();//创建JSch对象
        Session session = null;
        Channel channel = null;
        try {
            session = jsch.getSession(username,host,port);//根据用户名，主机ip，端口获取一个Session对象
            session.setPassword(password);//设置登录主机的密码
            session.setConfig("StrictHostKeyChecking", "no");//如果设置成“yes”，ssh就不会自动把计算机的密匙加入“$HOME/.ssh/known_hosts”文件，并且一旦计算机的密匙发生了变化，就拒绝连接。
            session.connect(connectTimeout);//设置登录超时时间
            /*
            调用openChannel(String type)
            可以在session上打开指定类型的channel。该channel只是被初始化，使用前需要先调用connect()进行连接。
            Channel的类型可以为如下类型：
            shell - ChannelShell
            exec - ChannelExec
            direct-tcpip - ChannelDirectTCPIP
            sftp - ChannelSftp
            subsystem - ChannelSubsystem
            其中，ChannelShell和ChannelExec比较类似，都可以作为执行Shell脚本的Channel类型。它们有一个比较重要的区别：ChannelShell可以看作是执行一个交互式的Shell，而ChannelExec是执行一个Shell脚本。
            */
            channel = session.openChannel("shell");//打开shell通道
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(channel.getOutputStream(),writeCharsetName));//写入该流的所有数据都将发送到远程端
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(channel.getInputStream(), readerCharsetName));//从远程端到达的所有数据都能从这个流中读取到
            channel.connect(connectTimeout);//建立shell通道的连接

            bufferedWriter.write(script);//写入该流的所有数据都将发送到远程端
            bufferedWriter.newLine();//经测试必须加writer.newLine(),流.read方法是按字节读取，只判断有没有读取到数据，不管内容的，所以换行符也会被读出来,而BufferedReader.readLine是按行读取的，即从当前位置一直读取数据，直到遇到换行符，然后去掉换行符，返回读取到的数据
            bufferedWriter.write("exit");//结束本次交互
            bufferedWriter.newLine();//经测试必须加writer.newLine()
            bufferedWriter.flush();//在调用flush或close以前,数据并没有被写入基础流,而是放在缓存区,调用后才被真正写入
            StringBuffer resultStringBuffer = new StringBuffer("");
            String resultString = "";
            while ((resultString = bufferedReader.readLine()) != null) {
                resultStringBuffer.append(resultString).append("\n");
            }
            int status = channel.getExitStatus();
            if (status != 0) {//错误状态
                throw new Exception("错误状态:" + status);
            }
            return resultStringBuffer.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
