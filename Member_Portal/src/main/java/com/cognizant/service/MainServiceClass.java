package com.cognizant.service;


import java.util.*;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.cognizant.modal.AuthResponse;
import com.cognizant.modal.DrugDetails;
import com.cognizant.modal.DrugStock;
import com.cognizant.modal.Prescription;
import com.cognizant.modal.RefillOrder;
import com.cognizant.modal.User;




import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MainServiceClass {
	
	//Adding new user in database
	public String AddUser(User user,HttpSession session,ModelMap model) {
		try
		{
		RestTemplate restTemplate1=new RestTemplate();
		User userobj=restTemplate1.postForObject("http://localhost:8090/authapp/adduser",user,User.class);
		String details="Hi "+user.getUname()+" Your details has been added to db successfully!..";
		model.put("details",details);
		return "login";
		}catch(Exception e)
		{
			String details="UserId is Already in use!.. Try with another userID!..";
			model.put("details",details);
			return "login";
		}
	}
	
   //Search drugs by Id
	public String drugbyid(int drugid, HttpSession session, ModelMap map) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
			map.put("expire","Your session is expired please login again!..");
			return "login";
        }
		try
		{
		RestTemplate restTemplate=new RestTemplate();
		DrugDetails drug=restTemplate.getForObject("http://localhost:8092/searchDrugsByID/"+drugid, DrugDetails.class);
		map.put("DrugName", drug.getName());
		map.put("Units", drug.getUnits());
		map.put("Cost", drug.getCost());
		map.put("Location",drug.getLocation());
		map.put("Manufacturer",drug.getManufacturer());
		map.put("ManufacturedDate",drug.getManufacturedDate());
		map.put("ExpiryDate",drug.getExpiryDate());
		map.put("MedicalComposition",drug.getMedicalComposition());
		}catch(Exception e)
		{
			String msg="No Drugs available for this drugId "+drugid;
			map.put("notfoundid", msg);
		}

		return "drugs";
	}

	//Search drugs by name
	public String drugbyname(String drugName, HttpSession session, ModelMap map) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
			map.put("expire","Your session is expires please login again!..");
			return "login";
        }
		try
		{
		RestTemplate restTemplate=new RestTemplate();
		DrugDetails drug=restTemplate.getForObject("http://localhost:8092/searchDrugsByName/"+drugName, DrugDetails.class);
		map.put("DrugName1", drug.getName());
		map.put("Units1", drug.getUnits());
		map.put("Cost1", drug.getCost());
		map.put("Location1",drug.getLocation());
		map.put("Manufacturer1",drug.getManufacturer());
		map.put("ManufacturedDate1",drug.getManufacturedDate());
		map.put("ExpiryDate1",drug.getExpiryDate());
		map.put("MedicalComposition1",drug.getMedicalComposition());
		}
		catch(Exception e)
		{
			String msg="No Drugs available for this drugName "+drugName;
			map.put("notfoundname", msg);
		}
		return "drugs";
	}

	//get all available drugs
	public String getAllDrugs(HttpSession session, ModelMap map) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
			map.put("expire","Your session is expires please login again!..");
			return "login";
        }
		try
		{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		DrugDetails drugdetails=new DrugDetails();
		HttpEntity<DrugDetails> entity = new HttpEntity<DrugDetails>(drugdetails, headers);
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		ResponseEntity<List<DrugDetails>> responseEntity = restTemplate.exchange("http://localhost:8092/alldrugs", HttpMethod.GET, entity,  new ParameterizedTypeReference<List<DrugDetails>>() {});
		
		List<DrugDetails> emps = responseEntity.getBody();
		Map<Integer,String> drugmap=new HashMap<>();
		for(DrugDetails obj:emps)
		{
			drugmap.put(obj.getId(),obj.getName());	
		}
		map.put("drugmap", drugmap);
		}catch(Exception e)
		{
			map.put("drugmap", "There is some error while retrieving all drugs!.. ");
			return "success";
		}
		return "drugs";
	}

	//login authorization
	public String validate(User user, ModelMap model, HttpSession session) {
		User userobj=null;
		RestTemplate restTemplate=new RestTemplate();
		try {
			userobj=restTemplate.postForObject("http://localhost:8090/authapp/login", user, User.class);
			log.info("inside validate success");
		}
		catch(Exception e)
		{
			log.info("inside validate error");
			String error="";
			error="Unable to login. please check your credentials.";
			log.info("inside validate error2");
			model.put("error", error);
			return "login";
		}
		session.setAttribute("token", userobj.getAuthToken());
		session.setAttribute("memberId", userobj.getUserid());
		return getWelcome((String) session.getAttribute("token"));
	}

	 public String getWelcome(String token) {
		 RestTemplate restTemplate=new RestTemplate();
		try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
			@SuppressWarnings({ "rawtypes", "unchecked" })
			HttpEntity request = new HttpEntity(headers);
			ResponseEntity<AuthResponse> response = restTemplate.exchange("http://localhost:8090/authapp/validate", HttpMethod.GET, request, AuthResponse.class);
			AuthResponse account = response.getBody();
		} catch (Exception e) {
			return "login";
		}
		return "home";

	}

     //get drugs availability in a given location
	public String getstocksbyId(int drugID, String location, HttpSession session, ModelMap map) {

	
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
			map.put("expire","Your session is expires please login again!..");
			return "login";
        }
		try
		{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		DrugDetails drugdetails=new DrugDetails();
		HttpEntity<DrugDetails> entity = new HttpEntity<DrugDetails>(drugdetails, headers);
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		ResponseEntity<List<DrugStock>> responseEntity = restTemplate.exchange("http://localhost:8092/getDispatchableDrugStock/"+drugID+"/"+location, HttpMethod.POST, entity,  new ParameterizedTypeReference<List<DrugStock>>() {});
		
		List<DrugStock> emps = responseEntity.getBody();
		List<Integer> quantity=new ArrayList<>();
		for(DrugStock obj:emps)
		{
			quantity.add(obj.getQuantity());		
		} 
		map.put("quantity","Available stocks for drugId "+drugID+" and location "+location+" is ="+quantity);
		}
		catch(Exception e)
		{
			map.put("drugidloc","No drugs available at this id "+drugID+" and location "+location);
		}

		return "drugs";
	}
    //subscription page
	public String Subscription(HttpSession session, ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
        RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		DrugDetails drugdetails=new DrugDetails();
		HttpEntity<DrugDetails> entity = new HttpEntity<DrugDetails>(drugdetails, headers);
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		ResponseEntity<List<DrugDetails>> responseEntity = restTemplate.exchange("http://localhost:8092/alldrugs", HttpMethod.GET, entity,  new ParameterizedTypeReference<List<DrugDetails>>() {});
		List<DrugDetails> emps = responseEntity.getBody();
		Map<Integer,String> drugmap=new HashMap<>();
		for(DrugDetails obj:emps)
		{
			drugmap.put(obj.getId(),obj.getName());
		}
		model.put("drugmap", drugmap);
		return "subscription";
	}
   //drugs page
	public String viewDrugs(HttpSession session, ModelMap model) {
		
		
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
		return "drugs";
	}

	public String Subscribe(Prescription pres,HttpSession session,ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
        try
        {
		RestTemplate restTemplate = new RestTemplate();
		String success=restTemplate.postForObject("http://localhost:8093/subscribe", pres, String.class);
		if(success.equalsIgnoreCase("Success Subscribe"))
		{
			model.put("success", "Successfully Subscribed!..");
		}
		else
		{
			model.put("error", "Ooops!..There is some exception so your subcription is not completed");
		}
		
        }
        catch(Exception e)
        {
        	model.put("error", "Ooops!..There is some exception so your subcription is not completed");
        }
		return "subscription";
		
	}

	public String Unsubscribe(long sid, int pid, HttpSession session, ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
        try
        {
		RestTemplate restTemplate = new RestTemplate();
		String unsubscribe=restTemplate.getForObject("http://localhost:8093/unsubscribe/"+sid+"/"+pid,String.class);
		if(unsubscribe.equalsIgnoreCase("Successfully unsubscribed"))
		{
			model.put("unsubscribe", "Successfully Unsubscribed!..");
		}
		else if(unsubscribe.startsWith("please"))
		{
			model.put("unsubscribe",unsubscribe);
		}
        }
        catch(Exception e)
        {
        	model.put("error1", "Ooops!..There is some exception so your Unsubcription is not completed");
        }
		return "subscription";
	}

	public String getRefillStatus(int id, HttpSession session, ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			RefillOrder refill=restTemplate.getForObject("http://localhost:8094/viewRefillStatus/"+id,RefillOrder.class);
			model.put("id",refill.getRefillId());
			model.put("sid",refill.getSubscriptionId());
			model.put("rdate",refill.getRefillDate());
			model.put("oid",refill.getRefillOrderId());
			model.put("quantity",refill.getQuantity());
			model.put("cost",refill.getCost());
			model.put("payment",refill.getPaymentStatus());
			
		}catch(Exception e)
		{
			model.put("refillerror", "Ooops!..There is some exception!...");
		}
		return "refill";
	}

	public String ViewRefill(HttpSession session, ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
		return "refill";
	}

	public String getRefillDuesAsOfDate(int id, HttpSession session, ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
        try
        {
        	RestTemplate restTemplate = new RestTemplate();
			RefillOrder refill=restTemplate.getForObject("http://localhost:8094/getRefillDuesAsOfDate/"+id,RefillOrder.class);
		   String status=refill.getPaymentStatus();
		   if(status.equalsIgnoreCase("paid"))
		   {
			   model.put("paid","No pending payments!..");
		   }
		   else if(status.equalsIgnoreCase("unpaid"))
		   {
			   model.put("paid","You need to pay "+refill.getCost());
		   }
		  
        }
        
        catch(Exception e)
        {
        	model.put("refillerror1", "Ooops!..There is some exception!...");
        }
		return "refill";
	}

	public String requestAdhocRefill(String memberId, int sid, String location, HttpSession session, ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
        try
        {
        	RestTemplate restTemplate = new RestTemplate();
    		String refill=restTemplate.postForObject("http://localhost:8094/requestAdhocRefill/"+1+"/"+sid+"/"+location,memberId,String.class);
            if(refill.equalsIgnoreCase("success"))
            {
    		model.put("available","Your Request is successfully completed!...");
            }
            else
            {
            	model.put("notavailable","Drug is not available at your location");
            }
        }catch(Exception e)
        {
        	model.put("notavailable","Drug is not available at your location");
        }
		return "refill";
	}

	public String Payment(int rid, int sid, int amount,HttpSession session, ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
        try
        {
        	RestTemplate restTemplate = new RestTemplate();
        	String refill=restTemplate.postForObject("http://localhost:8094/payment/"+rid+"/"+sid+"/"+amount,amount,String.class);
        	if(refill.startsWith("Balance"))
        	{
        		model.put("balance",refill);
        	}
        	else if(refill.startsWith("Thanks"))
        	{
        		model.put("balance",refill);
        	}
        	else
        	{
        		model.put("balance",refill);
        	}
        }
        catch(Exception e)
        {
        	model.put("balance","Ooops!..There is some exception!...");
        }
		return "refill";
	}
	

}
//https://www.youtube.com/watch?v=Jq7qA4VLV04
//from Super User 065 to All Attendees:
//https://www.youtube.com/watch?v=2oXVYxIPs88
