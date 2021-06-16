package com.yc.test;
import org. junit.Test;
import com. yc .bean . MenberInfo;
import com. yc.biz .MenberBiz;
public class MenberBizTest {
MenberBiz biz = new MenberBiz() ;

	@Test
	public void testLogin() throws Exception {
		MenberInfo m = new MenberInfo();
		m.setNickName("hcl");
		m.setPwd("a");
		System.out.println(biz.login(m));
	}
}