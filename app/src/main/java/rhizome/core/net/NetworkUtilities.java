package rhizome.core.net;

import io.activej.bytebuf.ByteBuf;

public class NetworkUtilities {

    NetworkUtilities() {}

    public static int readNetworkUint32(ByteBuf buffer) {
        int x = buffer.readInt();
        return networkToHostUint32(x);
    }

    public static long readNetworkUint64(ByteBuf buffer) {
        long x = buffer.readLong();
        return networkToHostUint64(x);
    }

    public static byte[] readNetworkSHA256(ByteBuf buffer) {
        byte[] h = new byte[32];
        buffer.read(h);
        return h;
    }

    public static byte[] readNetworkPublicWalletAddress(ByteBuf buffer, int addressLength) {
        byte[] w = new byte[addressLength];
        buffer.read(w);
        return w;
    }

    public static void readNetworkNBytes(ByteBuf buffer, byte[] outBuffer, int N) {
        buffer.read(outBuffer, 0, N);
    }

    public static void writeNetworkUint32(ByteBuf buffer, int x) {
        x = hostToNetworkUint32(x);
        buffer.writeInt(x);
    }

    public static void writeNetworkUint64(ByteBuf buffer, long x) {
        x = hostToNetworkUint64(x);
        buffer.writeLong(x);
    }

    public static void writeNetworkSHA256(ByteBuf buffer, byte[] x) {
        if (x.length != 32) throw new IllegalArgumentException("Invalid SHA256 length");
        buffer.write(x);
    }

    public static void writeNetworkPublicWalletAddress(ByteBuf buffer, byte[] x, int addressLength) {
        if (x.length != addressLength) throw new IllegalArgumentException("Invalid address length");
        buffer.write(x);
    }

    public static void writeNetworkNBytes(ByteBuf buffer, byte[] inputBuffer, int N) {
        buffer.write(inputBuffer, 0, N);
    }

    private static int networkToHostUint32(int networkValue) {
        return Integer.reverseBytes(networkValue);
    }

    private static long networkToHostUint64(long networkValue) {
        return Long.reverseBytes(networkValue);
    }

    private static int hostToNetworkUint32(int hostValue) {
        return Integer.reverseBytes(hostValue);
    }

    private static long hostToNetworkUint64(long hostValue) {
        return Long.reverseBytes(hostValue);
    }
}
