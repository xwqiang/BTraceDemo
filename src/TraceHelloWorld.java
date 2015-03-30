import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;
import static com.sun.btrace.BTraceUtils.strcat;
import static com.sun.btrace.BTraceUtils.timeMillis;

import com.sun.btrace.BTraceUtils.Sys;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.OnTimer;
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
	public static void endMethod(@ProbeClassName String name,@ProbeMethodName String method,int sleepTime){
		println(strcat("the class name=>", name));
		println(strcat("the class method=>", method));
		println(strcat("the class method params=>", str(sleepTime)));
		println(strcat("annotation method time comsumed :", str(timeMillis()-startTime)));
		counter ++;
		println( strcat("total times :",str(counter)));

	}
//	@OnMethod(clazz = "/..+/", method = "/..+/", location = @Location(Kind.RETURN))
//	public static void endMethod1(){
//		println(strcat("all method:", str(timeMillis()-startTime)));
//		counter ++;
//		println( strcat("total click times: ",str(counter)));
//		
//	}
	
//	@OnMethod(clazz = "/my.app..+/", method = "@/..+/", location = @Location(Kind.RETURN))
//	public static void traceExecute(@ProbeClassName String name,@ProbeMethodName String method,int sleepTime){
//		println(strcat("the class name=>", name));
//		println(strcat("the class method=>", method));
//		println(strcat("the class method params=>", str(sleepTime)));
//		
//	}
	@OnTimer(4000)
	public static void printMem() {
		println("Heap:");
		println(Sys.Memory.heapUsage());
		println("Non-Heap:");
		println(Sys.Memory.nonHeapUsage());
	}
}
