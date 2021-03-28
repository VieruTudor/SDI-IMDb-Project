package networking;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Message {
/**
* For the call
* header = "Controller: Method"
* body = method parameters serialized as csv
* For the response
* header = either "success" or "exception"
* body = either the csv response or the exception message
*/
    private final String header;
    private List<String> body = new LinkedList<>();

    public Message(String header, String... body)
    {
        this.header = header;
    }

    public static void write(Message message, OutputStream stream) throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(stream);
        dataOutputStream.writeUTF(message.header);
        dataOutputStream.writeInt(message.body.size());

        message.body.forEach(row -> {
            try {
                dataOutputStream.writeUTF(row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Message read(InputStream inputStream) throws IOException
    {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String header = dataInputStream.readUTF();
        Message message = new Message(header);
        int bodySize = dataInputStream.readInt();
        IntStream.range(0, bodySize).forEach(row -> {
            try {
                message.addRow(dataInputStream.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return message;
    }

    public void addRow(String string)
    {
        body.add(string);
    }

    public String getHeader()
    {
        return header;
    }

    public List<String> getBody()
    {
        return body;
    }

    public void setBody(List<String> body)
    {
        this.body = body;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "header='" + header + '\'' +
                ", body=" + body +
                '}';
    }
}
