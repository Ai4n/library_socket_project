package Main;

import java.util.ArrayList;
import com.google.gson.*;

public class MainTestClass {
	
	public static void main(String[] args) {
		ThreadTest tt1 = new ThreadTest(10, 1, 987654321);
		ThreadTest tt2 = new ThreadTest(5, 2, 878787878);
		ThreadTest tt3 = new ThreadTest(1, 3, 567777777);

		tt1.start();
		tt2.start();
		tt3.start();
	}


}
