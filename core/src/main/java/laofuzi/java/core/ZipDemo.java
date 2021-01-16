package laofuzi.java.core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.Test;

public class ZipDemo {
	@Test
	public void readZip() { 
		try (ZipInputStream zis = new ZipInputStream(new FileInputStream("/Users/lx/temp/tde/tde.zip"))) {
            ZipEntry entry = null;
            String finderDir = null;
            while ((entry = zis.getNextEntry()) != null) {
            	
            	if(entry.isDirectory()) {
            		finderDir = entry.getName();
            		System.out.println("finder:" + finderDir);
            	} else {
            		final String filename = entry.getName();
                    
            	}
            	
            	
            	//entry.get
                

                

                 

                zis.closeEntry();
            }

             
        } catch (final Exception e) {
        	 e.printStackTrace();

        } 
	}
}
