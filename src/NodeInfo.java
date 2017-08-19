public class NodeInfo {
    private String nodename;
    private String nodeIP;
    private int nodeport;
    private String max;
    private String remain;
    private int number;
    private boolean usable;

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRemain() {

        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getMax() {

        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public int getNodeport() {

        return nodeport;
    }

    public void setNodeport(int nodeport) {
        this.nodeport = nodeport;
    }

    public String getNodeIP() {

        return nodeIP;
    }

    public void setNodeIP(String nodeIP) {
        this.nodeIP = nodeIP;
    }
}
