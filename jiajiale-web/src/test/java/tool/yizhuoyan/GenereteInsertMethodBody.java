package tool.yizhuoyan;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class GenereteInsertMethodBody {

	public static void main(String[] args) {
		//run(SysDictionary.class);
	}
	
	
	
	public static void run(Class type) {
		StringBuilder result=new StringBuilder();
		result.append("\tint i = 1;\n");
		Method[] methods = type.getMethods();
		List<Method> methodsList=new ArrayList<>(methods.length/2);
		String methodName=null;
		for (Method method : methods) {
			methodName=method.getName();
			if((methodName.startsWith("get")||
					methodName.startsWith("is"))&&
					method.getParameterCount()==0&&
					!methodName.equals("getClass")) {
				methodsList.add(method);
				
			}
		}
		Collections.sort(methodsList,(a,b)->{
			return a.getName().compareTo(b.getName());
		});
		for (Method method : methodsList) {
			generateSetStatement(result, method);
		}
		System.out.println(result);
	}
	private static void generateSetStatement(StringBuilder result,Method m) {
		Class type=m.getReturnType();
		
		String typeName="Object";
		String value="e."+m.getName()+"()";
		if(type==String.class) {
			typeName="String";
		}else if(type==int.class||type==Integer.class) {
			typeName="Int";
		}else if(type==boolean.class||type==Boolean.class) {
			typeName="Boolean";
		}else if(type==long.class||type==Long.class) {
			typeName="Long";
		}else if(type==float.class||type==Float.class) {
			typeName="Float";
		}else if(type==double.class||type==Double.class) {
			typeName="Double";
		}else if(type==java.util.Date.class) {
			typeName="Timestamp";
			value="utilDate2timestamp("+value+")";
		}else if(type==java.time.Instant.class) {
			typeName="Timestamp";
			value="instant2timestamp("+value+")";
		}else if(type==java.time.LocalDate.class) {
			typeName="Date";
			value="localDate2sqlDate("+value+")";
		}else if(type==java.time.LocalTime.class) {
			typeName="Time";
			value="localTime2sqlTime("+value+")";
		}else if(type==java.time.LocalDateTime.class) {
			typeName="Timestamp";
			value="localDateTime2timestamp("+value+")";
		}else if(Collection.class.isAssignableFrom(type)) {
			//miss
		}else{//may be entity
			result.append("\tps.setString(i++,");
			result.append(value).append("==null?null:")
			.append(value).append(".getId());\n");
			return;
		}
		result.append("\tps.set");
		result.append(typeName);
		result.append("(i++,").append(value).append(");\n");
	}
	static private String lowerFirstChar(String s) {
		char[] cs=s.toCharArray();
		cs[0]=Character.toLowerCase(cs[0]);
		return new String(cs);
	}
}
