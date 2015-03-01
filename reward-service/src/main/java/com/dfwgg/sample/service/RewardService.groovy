package com.dfwgg.sample.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.dfwgg.sample.dao.RewardDao
import com.dfwgg.sample.model.PointEvent
import com.dfwgg.sample.model.RecordPointsRequest
import com.dfwgg.sample.model.UserRewards

@Service
class RewardService{
	
	@Autowired
	RewardDao rewardDao 
	
     UserRewards recordUserPointEvent(RecordPointsRequest request){
		 UserRewards userRewardRecord = rewardDao.findByUserName(request.userName)
		 if(userRewardRecord){
			 userRewardRecord.setTotalPoints(userRewardRecord.getTotalPoints() + request.pointEvent.getPoints())
			 userRewardRecord.getPointEvents().add(request.pointEvent)
			 userRewardRecord = rewardDao.save(userRewardRecord)
		 }
		 else
		 {
			 UserRewards newUserRewardRecord = new UserRewards()
			 newUserRewardRecord.userName = request.userName
			 newUserRewardRecord.totalPoints = request.pointEvent.points
			 newUserRewardRecord.pointEvents = new ArrayList<PointEvent>()
			 newUserRewardRecord.pointEvents.add(request.pointEvent)
			 userRewardRecord =  rewardDao.save(newUserRewardRecord)
		 }
		 userRewardRecord
	 }
	 
	 UserRewards getUserRewardRecords(String userName){
		 UserRewards userRewardRecord = rewardDao.findByUserName(userName)
		 userRewardRecord
	 }
	 
}
