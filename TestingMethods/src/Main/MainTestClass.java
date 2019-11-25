package Main;

import java.util.ArrayList;
import com.google.gson.*;

public class MainTestClass {
	
	public static void main(String[] args) {
		TestClass testObject = new TestClass(1, "test", new TestNestedClass(5));
		Gson gson = new Gson();
		String json = gson.toJson(testObject);
		
		System.out.println(json);
	}


}
