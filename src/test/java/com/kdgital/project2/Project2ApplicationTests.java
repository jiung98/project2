package com.kdgital.project2;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kdgital.project2.entity.AnnounceEntity;
import com.kdgital.project2.entity.CountryEntity;
import com.kdgital.project2.entity.CsAnswerEntity;
import com.kdgital.project2.entity.CsEntity;
import com.kdgital.project2.entity.DemandEntity;
import com.kdgital.project2.entity.FaqEntity;
import com.kdgital.project2.entity.DashboardEntity;
import com.kdgital.project2.entity.UserEntity;
import com.kdgital.project2.entity.NewsEntity;
import com.kdgital.project2.entity.RegionEntity;
import com.kdgital.project2.entity.TradeEntity;
import com.kdgital.project2.repository.AnnounceRepository;
import com.kdgital.project2.repository.CountryRepository;
import com.kdgital.project2.repository.CsAnswerRepository;
import com.kdgital.project2.repository.CsRepository;
import com.kdgital.project2.repository.DemandRepository;
import com.kdgital.project2.repository.FaqRepository;
import com.kdgital.project2.repository.DashboardRepository;
import com.kdgital.project2.repository.MemberRepository;
import com.kdgital.project2.repository.NewsRepository;
import com.kdgital.project2.repository.RegionRepository;
import com.kdgital.project2.repository.TradeRepository;

// test코드로 화면에서 오는 정보르 dto로 받아오는걸 임의로 진행하는거 

@SpringBootTest
class Project2ApplicationTests {
	@Autowired  //객체생성하는거 
	private MemberRepository aa;
	
	@Autowired
	private AnnounceRepository bb;
	
	@Autowired
	private CountryRepository cc;
	
	@Autowired
	private CsAnswerRepository dd;
	
	@Autowired
	private CsRepository ee;
	
	@Autowired
	private DemandRepository ff;
	
	@Autowired
	private FaqRepository gg;
	
	@Autowired
	private DashboardRepository hh;
	
	@Autowired
	private NewsRepository ii;
	
	@Autowired
	private RegionRepository jj;
	
	@Autowired
	private TradeRepository kk;
	
	@Test
	void contextLoads() {
	}
//	@Test
//	void memberTest() {
//		UserEntity a = new UserEntity();
//		a.setUserId("s234f");
//		a.setUserPwd("df");
//		a.setBirth("dfdf");
//		a.setEmail("12coel56");
//		//a.setUserAdd("s123sdf");
//		a.setPhone("1232555554");
//		a.setRoles("ROLE_USER");
//		a.setEnabled(true);
//		
//		System.out.println(a);
//		aa.save(a);		
//	}
//	@Test
//	void announceTest() {
//		AnnounceEntity a =new AnnounceEntity();
//		a.setTitle("안녕");
//		a.setContent("6월 28일기준으로 업데이트를 완료했습니다.");
//		
//		bb.save(a);
//	}
//	@Test
//	void countryTest() {
//		CountryEntity a =new CountryEntity();
//		a.setCCode("AL");
//		a.setCountry("아르헨티나");
//		
//		cc.save(a);
//	}
//	@Test
//	void csAnswerTest() {
//		CsAnswerEntity a =new CsAnswerEntity();
//		a.setAnswer("이거는 이렇게 해야합니다12.");
//		
//		dd.save(a);
//	}
//
//	@Test
//	void demandTest() {
//		DemandEntity a =new DemandEntity();
//		a.setDemandDate(LocalDate.now());
//		a.setJpGk(null);
//		a.setJpIk(null);
//		a.setJpYk(null);
//		a.setUsaYk(null);
//		a.setUsaGs(null);
//		a.setUsaGk(null);
//		a.setDemand(null);//notnull 이라서 그럼 
//		
//		ff.save(a);
//	}
//	@Test
//	void faqTest() {
//		FaqEntity a =new FaqEntity();
//		a.setAnswer("대답은 이거");
//		a.setQuestion("질문은 이거");
//		
//		gg.save(a);
//	}
//	@Test
//	void mainServiceTest() {
//		MainServiceEntity a =new MainServiceEntity();
//		a.setMainDate(LocalDate.now());
//		a.setProduction(null);
//		a.setRegionEntity(null);
//		a.setTemperature(null);
//		
//		hh.save(a);
//	}
	@Test
	void newsTest() {
		NewsEntity a =new NewsEntity();
		a.setMedia("SBS");
		a.setPostdate(LocalDate.of(2024, 06, 18));
		a.setTitle("'더운 바다'로 늦어진 김 농사…생산량 '비상'");
		a.setUrl("https://n.news.naver.com/mnews/article/055/0001197467?sid=102");
		
		ii.save(a);
	}
//	@Test
//	void regionTest() {
//		RegionEntity a =new RegionEntity();
//		a.setRegion("지역이름");
//		a.setRCode("23");
//		
//		jj.save(a);
//	}
//	@Test
//	void tradeTest() {
//		TradeEntity a =new TradeEntity();
//		a.setCountryEntity(null);
//		a.setTradeDate(LocalDate.now());
//		a.setExport(null);
//		a.setImportC(null);
//		a.setRegionEntity(null);
//		
//		kk.save(a);
//	}
}
