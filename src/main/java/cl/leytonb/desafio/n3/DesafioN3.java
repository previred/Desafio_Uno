package cl.leytonb.desafio.n3;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.leytonb.desafio.Client;
import cl.leytonb.desafio.ClientException;
import cl.leytonb.desafio.Desafio;
import cl.leytonb.desafio.GeneradorResp;

@SpringBootApplication
@Controller
@RequestMapping("/ws/api/desafio")
public class DesafioN3 {

	public static void main(String[] args) {
		SpringApplication.run(DesafioN3.class, args);
	}

	@GetMapping("/missingDates")
	public ResponseEntity<MissingDates> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		try {
			GeneradorResp dates = new Client(null).getDates();
			List<LocalDate> missingDates = Desafio.getMissingDates(dates);
			MissingDates resp = new MissingDates(dates, missingDates);
			return new ResponseEntity<MissingDates>(resp, HttpStatus.OK);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}