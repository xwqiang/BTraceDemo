import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;
import static com.sun.btrace.BTraceUtils.strcat;
import static com.sun.btrace.BTraceUtils.timeMillis;


import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.TLS;
@BTrace
public class TraceHelloWorld {
	
	@TLS
	private static long startTime = 0;
	private static long counter = 0;
	
	@OnMethod(clazz = "/my.app..+/", method = "@/..+/")
	public static void startMethod(){
		startTime = timeMillis();
	}
	
	@OnMethod(clazz = "/my.app..+/", method = "@/..+/")
	public static void endMethod(){
		println(strcat("the class method execute time=>", str(timeMillis()-startTime)));
		counter ++;
		println( strcat("total click times: ",str(counter)));

	}
	@OnMethod(clazz = "*", method = "*", location = @Location(Kind.RETURN))
	public static void endMethod1(){
		println(strcat("aaa", str(timeMillis()-startTime)));
		counter ++;
		println( strcat("total click times: ",str(counter)));
		
	}
	
	@OnMethod(clazz = "my.app.test.HelloWorld", method = "execute", location = @Location(Kind.RETURN))
	public static void traceExecute(@ProbeClassName String name,@ProbeMethodName String method,int sleepTime){
		println(strcat("the class name=>", name));
		println(strcat("the class method=>", method));
		println(strcat("the class method params=>", str(sleepTime)));
		
	}
}
