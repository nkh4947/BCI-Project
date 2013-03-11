package org.tizen.common.util.asm.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
    public static byte[] toByteArray(InputStream pInputStream) throws IOException {
        try {
            if (pInputStream == null) {
                return null;
            }
    
            int lBufferSize = 1024;
            byte[] lByteBuffer = new byte[lBufferSize];
    
            int lBytesRead = 0;
    
            ByteArrayOutputStream lByteArrayOutputStream = null; 
            try { 
                lByteArrayOutputStream = new ByteArrayOutputStream(lBufferSize * 2);
                while ((lBytesRead = pInputStream.read(lByteBuffer)) != -1) {
                    lByteArrayOutputStream.write(lByteBuffer, 0, lBytesRead);
                }
        
                byte[] lDataBytes = lByteArrayOutputStream.toByteArray();
                return lDataBytes;
            }
            finally {
                if(lByteArrayOutputStream != null ) {
                    lByteArrayOutputStream.close();
                }
            }
        }
        finally {
            if(pInputStream != null ) {
                pInputStream.close();
            }
        }
    }
}
