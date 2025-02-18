package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/") // 루트 경로인 /의 웹 요청을 처리
	public String home() {
		return "home"; // 뷰 이름을 반환한다.
	}
}
