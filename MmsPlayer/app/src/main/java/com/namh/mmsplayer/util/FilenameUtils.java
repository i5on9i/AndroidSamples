package com.namh.mmsplayer.util;

/**
 * This code is from the file FilenameUtils.java of apache.common.io
 *
 *
 *
 * Created with IntelliJ IDEA.
 * User: namh
 * Date: 13. 10. 5
 * Time: 오후 1:25
 * To change this template use File | Settings | File Templates.
 */
public class FilenameUtils {
    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';


    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        return filename.substring(index + 1);
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

}
