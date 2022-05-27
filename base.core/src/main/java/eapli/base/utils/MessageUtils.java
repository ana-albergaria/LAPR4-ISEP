package eapli.base.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageUtils {

    public static void readMessage(byte[] message, DataInputStream sIn) throws IOException {
        sIn.read(message, 0, 4);
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


}
