package eapli.base.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessageUtils {

    public static void readMessage(byte[] message, DataInputStream sIn) throws IOException {
        sIn.read(message, 0, 4);
    }

    public static void writeMessage(byte code, DataOutputStream sOut) throws IOException {
        byte[] message = {(byte) 0, code, (byte) 0, (byte) 0};
        sOut.write(message);
        sOut.flush();
    }

    public static String getDataFromMessage(byte[] message, DataInputStream sIn) throws IOException {
        String data;

        int dataLength = message[2] + 256 * message[3];
        if(dataLength != 0) {
            byte[] messageData = new byte[dataLength];
            sIn.read(messageData,0,dataLength);
            data = new String(messageData, 0, dataLength);
            return data;
        } else {
            throw new UnsupportedOperationException("There's no data!");
        }
    }

    public static void writeMessageWithData(byte code, String dataToSend, DataOutputStream sOut) throws IOException {
        byte[] data = new byte[300];
        data = dataToSend.getBytes();
        byte[] message = {(byte) 0, code, (byte) dataToSend.length(), (byte) 0};
        sOut.write(message, 0, 4);
        sOut.write(data,0,dataToSend.length());
        sOut.flush();
    }


}
