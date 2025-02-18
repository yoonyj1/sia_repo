package tacos;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class) // HomeController의 웹페이지 테스트, 스프링 MVC애플리케이션 형태로 테스트가 실행되도록 한다.
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testHomepage() throws Exception{
		mockMvc.perform(get("/")) // GET / 를 수행한다.
		.andExpect(status().isOk()) // HTTP 200이 되어야 하며
		.andExpect(view().name("home")) // home 뷰가 있어야함
		.andExpect(content().string(containsString("Welcome to ..."))); // 콘텐츠에 Welcome to ...가 포함되어야 한다.
		
		// 응답은 반드시 200 , 뷰이름은 반드시 home, 뷰에 welcome to ... 반드시 포함
		// 셋 중 하나라도 충족하지 않으면 테스트 실패
	}

}
