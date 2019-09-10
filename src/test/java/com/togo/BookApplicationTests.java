package com.togo;

import com.togo.wx.common.util.HttpUtil;
import com.togo.wx.common.util.WXUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookApplicationTests {

	@Autowired
	private HttpUtil httpUtil;
	@Autowired
	private WXUtil wxUtil;

	@Test
	public void contextLoads() {
	}

//	@Test
//	public void tt(){
//
//		String code = "023XmKGi1T2S2u0EHyKi1jcAGi1XmKG6";
//		String result = wxUtil.login(code);
//		System.out.println(result);
//		// 8m+73tCAXuSRG6FqSCUfDg==
//		// oK1g_5fgSW8OzcTEawiZRpAV7GV8
//	}

}
