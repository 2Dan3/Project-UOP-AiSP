package entities;

import java.util.*;

public class TaxiService {

    private static int taxIdNum;

    private static String location;

    private static String name;

    private static double startingPrice;

    private static double pricePerKm;

    
	public static Dispatcher findDispatcher(long jmbG) {

		for(Dispatcher dp : Dispatcher.allDispatchers) {
			if (dp.getJmbg() == jmbG) {
				return dp;
			}
		}
		return null;
	}

    
    
    
}