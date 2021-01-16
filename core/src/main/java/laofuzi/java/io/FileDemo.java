package laofuzi.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileDemo {
	
	public List<String> listDir(String dir, int depth) {
		List<String> result = new ArrayList<>();
		listDir(result, dir, depth, 0);
		return result;
	}
	
	
	public void listDir(List<String> result, String dir, int targetDepth, int previousDepth) {
		int currentDepth = previousDepth + 1;
		
		//超过目标深度
		if(currentDepth > targetDepth) {
			return;
		}
		
		//列出下级目录
		File file = new File(dir);
		String[] dirs = file.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		
		//符合目标深度
		if(currentDepth == targetDepth) {
			if(dirs != null) {
				for(String item :  dirs) {
					result.add(Paths.get(dir, item).toString());
				}
			}
			return;
		}
		
		//小于目标深度
		if(dirs != null) {
			for(String item :  dirs) {
				String nestedDir = Paths.get(dir, item).toString();
				listDir(result, nestedDir, targetDepth, currentDepth);
			}
		}
	}
	
	@Test
	public void testList() {
		List<String> dirs = this.listDir("src", 1);
		for(String dir : dirs) {
			System.out.println(dir);
		}
	}
	
}
