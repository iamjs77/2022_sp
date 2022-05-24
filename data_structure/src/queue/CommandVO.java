package queue;

public class CommandVO {
    private String command;
    private String qName;
    private int qSize;
    private String msgId;
    private String msg;

    public CommandVO(String command, String qName, int qSize, String msgId, String msg) {
        this.command = command;
        this.qName = qName;
        this.qSize = qSize;
        this.msgId = msgId;
        this.msg = msg;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public int getqSize() {
        return qSize;
    }

    public void setqSize(int qSize) {
        this.qSize = qSize;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CommandVO{" +
                "command='" + command + '\'' +
                ", qName='" + qName + '\'' +
                ", qSize=" + qSize +
                ", msgId='" + msgId + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
