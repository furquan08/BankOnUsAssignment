package com.furquan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.furquan.entity.TemperatureConversion;

/**
 * @author furquan
 *
 */
@Controller
public class TemperatureController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service.url.convert.temp}")
	private String url;

	@RequestMapping(value = "/temperature", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "temperature";
	}

	@PostMapping("/temperatureConvert")
	public String convertTemperature(ModelMap model, @RequestParam String property, @RequestParam String val) {

		TemperatureConversion temperatureConversion = restTemplate
				.getForObject(url + "property=" + property + "&val=" + val, TemperatureConversion.class);

		model.addAttribute("Celsius", temperatureConversion.getCelsius());
		model.addAttribute("Farenheit", temperatureConversion.getFahrenheit());
		return "temperature-result";
	}

	
}
