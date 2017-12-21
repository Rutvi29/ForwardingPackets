
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;

public class UdpClientThread extends Thread{
    String dstAddress;
    int dstPort;
    DatagramSocket socket;
    InetAddress address;
    byte[] message;
    Timestamp t;
    public UdpClientThread(byte[] message,String addr, int port) {
        super();
        dstAddress = addr;
        dstPort = port;
        this.message=message;
        //System.out.println("port==="+port);
    }
    @Override
    public void run() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(dstAddress);
            DatagramPacket packet =
                    new DatagramPacket(message,message.length, address, dstPort);
            System.out.println("Dstport=="+dstPort);
            socket.send(packet);     
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null){
                socket.close();

            }
        }

    }
}
