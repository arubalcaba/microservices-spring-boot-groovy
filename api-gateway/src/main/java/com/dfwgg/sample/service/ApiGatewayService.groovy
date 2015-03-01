package com.dfwgg.sample.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate;

import com.dfwgg.sample.model.RecordPointsRequest;
import com.dfwgg.sample.model.User
import com.dfwgg.sample.model.UserRewards;

@Service
class ApiGatewayService {
	
	RestTemplate restTemplate = new RestTemplate()
	
	User createUser(User user){
		
		def result = restTemplate.postForObject("http://localhost:8090/user",user,User.class)
		result
	}
	
	String recordReward(RecordPointsRequest request){
		try{
			def user = restTemplate.getForObject("http://localhost:8090/user/${request.userName}",User.class)
			if(user){
				def userReward = restTemplate.postForObject("http://localhost:8080/reward/user/record", request,UserRewards.class)
				if(userReward)
					"Reward Recorded Successfully"
				else
					"Something went wrong"
			}
			else{
				"User does not exist"
			}
		}
		catch(Exception e){
			e.printStackTrace()
			"Something went wrong"
		}
	}
	
	UserRewards getUserRewards(String userName){
		def userRewards = restTemplate.getForObject("http://localhost:8080/reward/user/${userName}",UserRewards.class)
		userRewards
	}

}
