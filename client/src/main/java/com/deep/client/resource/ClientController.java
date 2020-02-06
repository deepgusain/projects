package com.deep.client.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	/*@Autowired
	private EurekaClient client; 

	@Autowired
	RestTemplateBuilder builder;

	
	@RequestMapping(method=RequestMethod.GET, path="/")
	public String callService(){
		RestTemplate restTemplate= builder.build();
		InstanceInfo info=client.getNextServerFromEureka("service", false);
		String baseUrl=info.getHomePageUrl();
		ResponseEntity<String> response=restTemplate.exchange(baseUrl, HttpMethod.GET,null, String.class);
		return response.getBody();
	}*/

	@GetMapping(path="/user")
	public String callService(){
		return "success";
	}
}
