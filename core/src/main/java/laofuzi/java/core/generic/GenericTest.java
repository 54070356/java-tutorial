package laofuzi.java.core.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericTest {
	@Test
	public void test1() {
		List<String> l = new ArrayList<>();
		
		System.out.println(l.getClass());
		
		System.out.println(Game[].class);
		
		Class<?> clazz = l.getClass();
		System.out.println(clazz.toGenericString());
		//TypeVariable<Class<>>[] type = clazz.getTypeParameters(); 
		
//		if(type instanceof ParameterizedType) {
//			ParameterizedType parameterizedType = (ParameterizedType) type;
//			Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();
//			if(actualTypeArgs.length > 0) {
//				System.out.println(actualTypeArgs[0]);
//			}
//	        
//		}
		


	}
}
