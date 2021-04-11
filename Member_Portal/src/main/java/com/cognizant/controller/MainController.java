package com.cognizant.controller;


import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.modal.Prescription;
import com.cognizant.modal.RefillOrder;
import com.cognizant.modal.User;
import com.cognizant.service.MainServiceClass;


import feign.FeignException;


@Controller
public class MainController {
	
	@Autowired
	MainServiceClass mainService;
	
	@GetMapping("/login")
	public String showFrontPage()
	{
		
		return "login";
	}
	
	@PostMapping("/home")
	public String success(@RequestParam("userId") String userId,@RequestParam("userName") String userName,
			@RequestParam("password") String password,ModelMap model, HttpSession session)
	{
		User user=new User();
	    user.setUserid(userId);
	    user.setUname(userName);
	    user.setUpassword(password);
		return mainService.validate(user, model,session);
	}
	
	@GetMapping("/homenew")
	public String getWelcome(HttpSession session,ModelMap model) {
		String token = (String) session.getAttribute("token");
        if(token == null)
        {
        	model.put("expire","You can't access the page without login!.. Try to login!...");
			return "login";
        }
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@PostMapping("/adduser")
	public String AddUser(@RequestParam("userName") String userName,@RequestParam("userId") String userId,
			@RequestParam("password") String password,ModelMap model,HttpSession session)
	{
		User user=new User();
	    user.setUserid(userId);
	    user.setUname(userName);
	    user.setUpassword(password);
		return mainService.AddUser(user,session,model);
	}
	
	
	//Drug Service
	@GetMapping("/drugs")
	public String viewDrugs(HttpSession session,ModelMap model)
	{
		return mainService.viewDrugs(session,model);
	}
	@GetMapping("/drugbyid")
	public String drugbyid(@RequestParam("drugid") int drugid, HttpSession session,ModelMap map)
	{
		return mainService.drugbyid(drugid,session,map);
	}
	
	@GetMapping("/DrugsByName")
	public String drugbyname(@RequestParam("drugName") String drugName, HttpSession session,ModelMap map)
	{
		return mainService.drugbyname(drugName,session,map);
	}
	
	@GetMapping("/getallDrugs")
	public String getAllDrugs(HttpSession session,ModelMap map)
	{
		return mainService.getAllDrugs(session,map);
	}

	@GetMapping("/searchStock")
	public String getstocksbyId(@RequestParam("drugId") int drugID,@RequestParam("location") String location, HttpSession session,ModelMap map)
	{
		return mainService.getstocksbyId(drugID,location,session,map);
		
	}
	@GetMapping("/subscription")
	public String SubscriptionPage(HttpSession session,ModelMap model)
	{
		return mainService.Subscription(session,model);
	}
	
	@PostMapping("/subscribe")
	public String Subscribe(@RequestParam("prescriptionId") long pid,@RequestParam("memberLocation") String location,
			@RequestParam("policyNumber") String pol_number,@RequestParam("insurance") String insurance,
			@RequestParam("prescriptionDate")@DateTimeFormat(iso = ISO.DATE) LocalDate pdate,@RequestParam("drugName") String drugname,
			@RequestParam("doctorName") String doctor,@RequestParam("dosage") String dosage,@RequestParam("courseDuration") String duration,
			@RequestParam("quantity") int quantity,HttpSession session,ModelMap model)
	{
		String memberId = (String) session.getAttribute("memberId");
		Prescription pres=new Prescription();
		pres.setPrescriptionId(pid);
		pres.setMemberId(memberId);
		pres.setMemberLocation(location);
		pres.setPolicyNumber(pol_number);
		pres.setInsuranceProvider(insurance);
		pres.setPrescriptionDate(pdate);
		pres.setDrugName(drugname);
		pres.setDoctorName(doctor);
		pres.setDosageDefinition(dosage);
		pres.setQuantity(quantity);
		pres.setCourseDuration(duration);
		return mainService.Subscribe(pres,session,model);
	}
	
	@GetMapping("/unsubscribe")
	public String Unsubscribe(@RequestParam("subscriptionId") long sid,@RequestParam("prescriptionId") int  pid,HttpSession session,ModelMap model)
	{
		return mainService.Unsubscribe(sid,pid,session,model);
	}
	
	@GetMapping("/refill")
	public String ViewRefill(HttpSession session,ModelMap model)
	{
		return mainService.ViewRefill(session,model);
	}
	
	@GetMapping("/viewRefillStatus")
	public String getRefillStatus(@RequestParam("refillId") int id,HttpSession session,ModelMap model)
	{
		return mainService.getRefillStatus(id,session,model);
	}
	
	@GetMapping("/getRefillDuesAsOfDate")
	public String getRefillDuesAsOfDate(@RequestParam("subId") int id,HttpSession session,ModelMap model)
	{
		return mainService.getRefillDuesAsOfDate(id,session,model);
	}
	
	@PostMapping("/requestAdhocRefill")
	public String requestAdhocRefill(@RequestParam("subId") int sid,@RequestParam("location") String location,HttpSession session,ModelMap model)
	{
		String memberId = (String) session.getAttribute("memberId");
		return mainService.requestAdhocRefill(memberId,sid,location,session,model);
	}
	
	@PostMapping("/payment")
	public String Payment(@RequestParam("refillID") int rid,@RequestParam("subId") int sid,@RequestParam("amount") int amount,HttpSession session,ModelMap model)
	{
		return mainService.Payment(rid,sid,amount,session,model);
	}

}
