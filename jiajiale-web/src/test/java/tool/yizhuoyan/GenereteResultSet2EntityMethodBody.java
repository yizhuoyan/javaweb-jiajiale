package tool.yizhuoyan;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.vip.entity.SysUserEntity;

public class GenereteResultSet2EntityMethodBody {

	
	public static void main(String[] args) {
		run(SysUserEntity.class);
	}
	
	
	private static final Map<Class,Class> typeMappingMAP=new HashMap<Class,Class>(){{
		put(java.util.Date.class, java.sql.Timestamp.class);
		put(java.time.Instant.class, java.sql.Timestamp.class);
		put(java.time.LocalTime.class, java.sql.Time.class);
		put(java.time.LocalDate.class, java.sql.Date.class);
		put(java.time.LocalDateTime.class, java.sql.Timestamp.class);
	}};
	
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
				generateSetStatement(result, method);
			}
		}
		result.append("return e;");
		System.out.println(result);
	}
	private static void generateSetStatement(StringBuilder result,Method m) {
		Class type=m.getParameterTypes()[0];
		String methodName=m.getName();
		String fieldName=lowerFirstChar(methodName.substring(3));
		String columnName=toColumnName(fieldName);
		String conventer=null;
		String typeName="Object";
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
		}else if(type==java.time.Instant.class) {
			typeName="Timestamp";
			conventer="toInstant";
		}else if(type==java.time.LocalDate.class) {
			typeName="Date";
			conventer="toLocalDate";
		}else if(type==java.time.LocalTime.class) {
			typeName="Time";
			conventer="toLocalTime";
		}else if(type==java.time.LocalDateTime.class) {
			typeName="Timestamp";
			conventer="toLocalDateTime";
		}else {
			//do noting
			return;
		}
		result.append("e.").append(methodName);
		result.append("(");
		if(conventer!=null) {
			result.append(conventer).append("(");
		}
		result.append("rs.get").append(typeName);
		result.append("(\"");
		result.append(columnName);
		result.append("\")");
		if(conventer!=null) {
			result.append(")");
		}
		result.append(");\n");
	}
	
	static private String lowerFirstChar(String s) {
		char[] cs=s.toCharArray();
		cs[0]=Character.toLowerCase(cs[0]);
		return new String(cs);
	}
	static private String toColumnName(String fieldName) {
		StringBuilder result=new StringBuilder();
		for(int i=0;i<fieldName.length();i++) {
			char c=fieldName.charAt(i);
			if('A'<=c&&c<='Z') {
				result.append('_');
				c=(char)(c+32);
			}
			result.append(c);
		}
		return result.toString();
	}
}
