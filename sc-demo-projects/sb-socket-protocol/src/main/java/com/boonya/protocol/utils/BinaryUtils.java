package com.boonya.protocol.utils;

/**
 * @ClassName: BinaryUtils
 * @Description: TODO(功能说明 ： 二进制转换工具)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/30 0:09
 */
public class BinaryUtils {

    /**
     * int 转bytes
     *
     * @param value
     * @return
     */
    public static byte[] intToBytes2(int value) {
        byte[] src = new byte[]{(byte) (value >> 24 & 255), (byte) (value >> 16 & 255), (byte) (value >> 8 & 255), (byte) (value & 255)};
        return src;
    }

    /**
     * bytes 转 int
     *
     * @param src
     * @param offset
     * @return
     */
    public static int bytesToInt2(byte[] src, int offset) {
        int value = (src[offset] & 255) << 24 | (src[offset + 1] & 255) << 16 | (src[offset + 2] & 255) << 8 | src[offset + 3] & 255;
        return value;
    }

    /**
     * short 转bytes
     *
     * @param value
     * @return
     */
    public static byte[] shortToBytes2(short value) {
        byte[] src = new byte[]{(byte) (value >> 8 & 255), (byte) (value & 255)};
        return src;
    }

    /**
     * bytes转short
     *
     * @param src
     * @param offset
     * @return
     */
    public static short bytesToShort2(byte[] src, int offset) {
        short value = (short) ((src[offset] & 255) << 8 | src[offset + 1] & 255);
        return value;
    }

    /**
     * byte 转bytes
     *
     * @param value
     * @return
     */
    public static byte[] byteToBytes2(byte value) {
        byte[] src = new byte[]{value};
        return src;
    }

    /**
     * bytes 转byte
     *
     * @param src
     * @param offset
     * @return
     */
    public static byte bytesToByte(byte[] src, int offset) {
        byte value = src[offset];
        return value;
    }
}
