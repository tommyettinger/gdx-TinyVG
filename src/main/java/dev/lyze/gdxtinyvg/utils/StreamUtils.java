package dev.lyze.gdxtinyvg.utils;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import java.io.IOException;

public class StreamUtils {
    /**
     * This type is used to encode 32 bit unsigned integers while keeping the number
     * of bytes low. It is encoded as a variable-sized integer that uses 7 bit per
     * byte for integer bits and the 7th bit to encode that there is "more bits
     * available".
     *
     * @param stream The appropriately positioned input stream.
     * @return The actual int value of the VarUInt.
     */
    public static int readVarUInt(LittleEndianInputStream stream) throws IOException {
        int count = 0;
        int result = 0;
        while (true) {
            int b = stream.readUnsignedByte();
            int val = (b & 127) << (7 * count);
            result |= val;
            if ((b & 128) == 0)
                break;
            count++;
        }
        return result;
    }

    /**
     * Reads multiple consecutive bytes.
     * 
     * @param stream The appropriately positioned input stream.
     * @param amount How many bytes to read.
     * @return A byte array of all the read bytes.
     */
    public static int[] readNBytes(LittleEndianInputStream stream, int amount) throws IOException {
        int[] bytes = new int[amount];
        for (int i = 0; i < amount; i++)
            bytes[i] = stream.readUnsignedByte();
        return bytes;
    }
}
