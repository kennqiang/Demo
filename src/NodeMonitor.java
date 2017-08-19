import  javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class NodeMonitor extends JFrame{
    private ArrayList<String> nodeList;
    private DefaultTableModel tableModel;
    private JTable table ;
    public NodeMonitor(){
        super();
        setTitle("nodemonitor");
        setBounds(100,100,900,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String [] columenames = {"name","nodeIP","nodeport","max","remain","number","usable"};
        tableModel = new DefaultTableModel(null,columenames);
        table = new JTable(tableModel){
            public boolean isCellEditable(int row,int colume){
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(table);
        JPanel panel = new JPanel();
        getContentPane().add(panel,BorderLayout.SOUTH);
    }
    public static void main(String []args){
        NodeMonitor nodeMonitor = new NodeMonitor();
        nodeMonitor.setVisible(true);
        nodeMonitor.startMonitor();
    }
    public void startMonitor(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DatagramSocket dgs = new DatagramSocket(1111);
                            byte[] buffer = new byte[1024*1024];
                            while(true){
                                DatagramPacket dgp = new DatagramPacket(buffer,buffer.length);
                                dgs.receive(dgp);
                                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                                ObjectInputStream ois = new ObjectInputStream(bais);
                                NodeInfo nodeInfo = (NodeInfo) ois.readObject();
                                System.out.println(nodeInfo.toString());
                                showtotable(nodeInfo);
                            }
                        } catch (SocketException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
    public void showtotable(NodeInfo nodeInfo){
        String[] components =new String[7];
        components[0] = nodeInfo.getNodename();
        components[1] = nodeInfo.getNodeIP();
        components[2] = nodeInfo.getNodeport()+"";
        components[3] = nodeInfo.getMax();
        components[4] = nodeInfo.getRemain();
        components[5] = nodeInfo.getNumber()+"";
        components[6] = nodeInfo.isUsable()?"true":"false";
        if(!nodeList.contains(components[0])){
            nodeList.add(components[0]);
            tableModel.addRow(components);
        }
        else {
            int index = 0;
            for (int i = 0 ; i < nodeList.size(); i++){
                if(nodeList.get(i).equals(components[0])) {
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < 7; i++){
                tableModel.setValueAt(components[i],index,i);
            }
        }

    }
}
