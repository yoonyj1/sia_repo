package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList( // 현재는 DB가 없어서 직접 List에 하나씩 입력한것임.
				new Ingredient("FLTO", "Flour Tortlila", Type.WRAP),
				new Ingredient("COTO", "Corn Tortlila", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
				);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
		
		model.addAttribute("taco", new Taco());
		
		return "design";
		
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
	
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors) { // Valid annotaion은 Taco 객체의 validation과 매핑되어 유효성 검사 실시 후 에러 여부 확인
		if(errors.hasErrors()) { // 에러가 있을 경우 상세 내역이 Errors 객체에 저장되어  processDesign으로 전달됨
			return "design"; // 에러있을 경우 /design으로 다시 복귀
		}
		
		// 해당 메소드에서 타코 디자인(/design에서 선택하고 넘어온 식자재 내역)을 저장
		log.info("Processing Design: " + design);
		
		return "redirect:/orders/current";
	}
	
}
