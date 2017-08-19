import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class FileServer {
    private static ArrayList<NodeInfo> nodeInfoArrayList = new ArrayList<>();
    public static void connectnodemonitor(){
        try {
            DatagramSocket dgs = new DatagramSocket();
            while (true){
                for (int i = 0; i < nodeInfoArrayList.size(); i++){
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(nodeInfoArrayList.get(i));
                    DatagramPacket dgp = new DatagramPacket(baos.toByteArray(),baos.toByteArray().length);
                    dgp.setPort(1111);
                    dgs.send(dgp);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String []args){
        connectnodemonitor();
    }
}
