package action;

import java.util.*;

import javax.servlet.http.*;

import action.*;
import svc.*;
import vo.*;

public class MapAction  {


	public ArrayList<RestaurantInfoDTO> execute(String keyword) {
		System.out.println("MapAction - execute ");
		ActionForward forward = null;
		
		MapService service = new MapService();
		
		ArrayList<RestaurantInfoDTO> list = service.selectMapList(keyword);
		
		
		return list;
	}

}
