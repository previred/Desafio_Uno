package cl.fmanzana.CompletadorDesafio.dao.impl;

import org.springframework.web.client.RestTemplate;

import cl.fmanzana.CompletadorDesafio.dao.CompletadorDAO;

public class CompletadorDAOImpl implements CompletadorDAO {

	@Override
	public String obtienePeriodo() throws Exception {
		String data =  "";
		try {

			String url = "http://127.0.0.1:8080/periodos/api";

			RestTemplate restTemplate = new RestTemplate();
		    data = restTemplate.getForObject(url, String.class);
		   

		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return data;
	}

}
