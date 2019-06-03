package tool.yizhuoyan;

import java.lang.reflect.Method;
import java.util.Random;

import com.vip.vo.VipDetailVO;
import com.vip.vo.VipListVO;

public class GenereteSetterMethodBody {

	
	public static void main(String[] args) {
		run(VipDetailVO.class);
	}

	
	public static void run(Class type) {
		String className=type.getSimpleName(); 
		StringBuilder result=new StringBuilder();
		result.append(className).append(" e = new ").append(className).append("();\n");
		Method[] methods = type.getMethods();
		String methodName=null;
		for (Method method : methods) {
			methodName=method.getName();
			if((methodName.startsWith("set")&&
					method.getParameterCount()==1)) {
				//generateObjectSetterStatementByAo(result, method);
				//generateObjectSetterStatementForTest(result, method);
				generateObjectSetterStatementByVo(result, method);
			}
		}
		System.out.println(result);
	}
	
	private static void generateObjectSetterStatementForTest(StringBuilder result,Method m) {
		Class type=m.getParameterTypes()[0];
		String methodName=m.getName();
		String fieldName=lowerFirstChar(methodName.substring(3));
		String defaultValue=null;
		if(type==String.class) {
			defaultValue="\""+fieldName+generatePositiveInt(10000)+"\"";
		}else if(type==int.class||type==Integer.class) {
			defaultValue=String.valueOf(generatePositiveInt(100));
		}else if(type==boolean.class||type==Boolean.class) {
			defaultValue="false";
		}else if(type==long.class||type==Long.class) {
			defaultValue=String.valueOf(generatePositiveInt(100));
		}else if(type==float.class||type==Float.class) {
			defaultValue=String.valueOf(generatePositiveInt(100));
		}else if(type==double.class||type==Double.class) {
			defaultValue=String.valueOf(generatePositiveInt(100));
		}else if(type==java.util.Date.class) {
			defaultValue="new Date()";
		}else if(type==java.time.Instant.class) {
			defaultValue="Instant.now()";
		}else if(type==java.time.LocalDate.class) {
			defaultValue="LocalDate.now()";
		}else if(type==java.time.LocalTime.class) {
			defaultValue="LocalTime.now()";
		}else if(type==java.time.LocalDateTime.class) {
			defaultValue="LocalDateTime.now()";
		}else {
			defaultValue="null";
		}
		result.append("e.").append(methodName);
		result.append("(");
		result.append(defaultValue);
		result.append(");\n");
	}
	private static void generateObjectSetterStatementByAo(StringBuilder result,Method m) {
		Class type=m.getParameterTypes()[0];
		String methodName=m.getName();
		String fieldName=lowerFirstChar(methodName.substring(3));
		String defaultValue=fieldName;
		result.append("e.").append(methodName);
		result.append("(");
		result.append(defaultValue);
		result.append(");\n");
	}
	private static void generateObjectSetterStatementByVo(StringBuilder result,Method m) {
		Class type=m.getParameterTypes()[0];
		String methodName=m.getName();
		String fieldName=methodName.substring(3);
		String defaultValue="e.get"+fieldName+"()";
		result.append("vo.").append(methodName);
		result.append("(");
		result.append(defaultValue);
		result.append(");\n");
	}
	
	static private String lowerFirstChar(String s) {
		char[] cs=s.toCharArray();
		cs[0]=Character.toLowerCase(cs[0]);
		return new String(cs);
	}
	
	private static Random RANDOM=new Random();
	public static int generatePositiveInt(int max) {
		return RANDOM.nextInt(max);
	}
	
}

