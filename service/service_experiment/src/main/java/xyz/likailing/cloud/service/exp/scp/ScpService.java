package xyz.likailing.cloud.service.exp.scp;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @Author 12042
 * @create 2023/4/14 18:40
 */

public class ScpService {
    /**
     * 从服务器到本地
     * 
 * @param userName
 * @param password
 * @param ipAddr
 * @param clusterPath
 * @param localPath
     * @return boolean
     * @author likailing
     * @create 2023/4/14
     **/
    
    public static boolean getConfFile(String userName, String password, String ipAddr, String clusterPath, String localPath) {
        boolean isAuthed = false;
        boolean status = false;
        try {
            status = InetAddress.getByName(ipAddr).isReachable(1500);
            System.out.println(status);
            if (status) {
                Connection conn = new Connection(ipAddr);
                conn.connect();
                isAuthed = conn.authenticateWithPassword(userName, password);
                System.out.println(isAuthed);
                if (isAuthed) {
                    Session session = conn.openSession();
                    SCPClient scpClient = conn.createSCPClient();
                    scpClient.get(clusterPath, localPath);
                    session.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isAuthed;
    }
/**
 * 从本地到服务器
 * 
 * @param userName
 * @param password
 * @param ipAddr
 * @param loadFilePath
 * @param clusterPath
 * @return boolean
 * @author likailing
 * @create 2023/4/14
 **/

    public static boolean putBackConfFile(String userName, String password, String ipAddr, String loadFilePath, String clusterPath) {
        boolean isAuthed = false;
        try {
            if (InetAddress.getByName(ipAddr).isReachable(1500)) {
                Connection conn = new Connection(ipAddr);
                conn.connect();
                isAuthed = conn.authenticateWithPassword(userName, password);
                if (isAuthed) {
                    SCPClient scpClient = conn.createSCPClient();
                    scpClient.put(loadFilePath, clusterPath);
                    conn.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isAuthed;
    }
}
