package com.sds.demo.utils;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public final class ByteUtils {

    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public final static short UNSIGNED_MAX_VALUE = (Byte.MAX_VALUE * 2) + 1;

    public static short toUnsigned(byte b) {
        return (short) (b < 0 ? (UNSIGNED_MAX_VALUE + 1) + b : b);
    }

    public static String toHexAscii(byte b) {
        StringWriter sw = new StringWriter(2);
        addHexAscii(b, sw);
        return sw.toString();
    }

    public static String toHexAscii(byte[] bytes) {
        int len = bytes.length;
        StringWriter sw = new StringWriter(len * 2);
        for (int i = 0; i < len; ++i)
            addHexAscii(bytes[i], sw);
        return sw.toString();
    }


    public static String toHexString(byte[] var0) {
        String var1 = "";

        for (int var2 = 0; var2 < var0.length; ++var2) {
            var1 = var1 + HEX_CHARS[var0[var2] >>> 4 & 15];
            var1 = var1 + HEX_CHARS[var0[var2] & 15];
        }

        return var1;
    }

    public static byte[] reverse(byte[] bytes) {
        int len = bytes.length;
        byte[] newBytes = new byte[len];
        for (int i = 0; i < len; i++) {
            newBytes[i] = bytes[len - 1 - i];
        }

        return newBytes;
    }

    public static byte[] fromHexAscii(String s) throws NumberFormatException {
        try {
            int len = s.length();
            if ((len % 2) != 0)
                throw new NumberFormatException("Hex ascii must be exactly two digits per byte.");

            int out_len = len / 2;
            byte[] out = new byte[out_len];
            int i = 0;
            StringReader sr = new StringReader(s);
            while (i < out_len) {
                int val = (16 * fromHexDigit(sr.read())) + fromHexDigit(sr.read());
                out[i++] = (byte) val;
            }
            return out;
        } catch (IOException e) {
            throw new InternalError("IOException reading from StringReader?!?!");
        }
    }


    public static byte[] fromHexString(String var0) {
        char[] var1 = var0.toUpperCase().toCharArray();
        int var2 = 0;

        for (int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] >= 48 && var1[var3] <= 57 || var1[var3] >= 65 && var1[var3] <= 70) {
                ++var2;
            }
        }

        byte[] var6 = new byte[var2 + 1 >> 1];
        int var4 = var2 & 1;

        for (int var5 = 0; var5 < var1.length; ++var5) {
            if (var1[var5] >= 48 && var1[var5] <= 57) {
                var6[var4 >> 1] = (byte) (var6[var4 >> 1] << 4);
                var6[var4 >> 1] = (byte) (var6[var4 >> 1] | var1[var5] - 48);
            } else {
                if (var1[var5] < 65 || var1[var5] > 70) {
                    continue;
                }

                var6[var4 >> 1] = (byte) (var6[var4 >> 1] << 4);
                var6[var4 >> 1] = (byte) (var6[var4 >> 1] | var1[var5] - 65 + 10);
            }

            ++var4;
        }

        return var6;
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }


    static void addHexAscii(byte b, StringWriter sw) {
        short ub = toUnsigned(b);
        int h1 = ub / 16;
        int h2 = ub % 16;
        sw.write(toHexDigit(h1));
        sw.write(toHexDigit(h2));
    }

    private static int fromHexDigit(int c) throws NumberFormatException {
        if (c >= 0x30 && c < 0x3A)
            return c - 0x30;
        else if (c >= 0x41 && c < 0x47)
            return c - 0x37;
        else if (c >= 0x61 && c < 0x67)
            return c - 0x57;
        else
            throw new NumberFormatException('\'' + c + "' is not a valid hexadecimal digit.");
    }

    /* note: we do no arg. checking, because     */
  /* we only ever call this from addHexAscii() */
  /* above, and we are sure the args are okay  */
    private static char toHexDigit(int h) {
        char out;
        if (h <= 9) out = (char) (h + 0x30);
        else out = (char) (h + 0x37);
        //System.err.println(h + ": " + out);
        return out;
    }

    private ByteUtils() {
    }
}
