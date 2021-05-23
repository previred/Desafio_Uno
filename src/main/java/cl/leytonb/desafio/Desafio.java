package cl.leytonb.desafio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Desafio {

	private static final Logger logger = Logger.getLogger(Desafio.class.getName());

	public static void main(String[] args) {

		try {
			GeneradorResp resp = new Client(args).getDates();
			List<LocalDate> missingDates = getMissingDates(resp);
			String filename = getFilename(args);
			Files.createFile(resp, missingDates, filename);
			System.out.println("output file: " + filename);
		} catch (ClientException | IOException e) {
			logger.log(Level.ALL, e.getMessage());
		}

	}

	private static String getFilename(String[] args) {
		String filename = "Desafio.txt";
		if (args != null && args.length > 0) {
			if (args.length > 1) {
				filename = args[1];
			} else if (!args[0].startsWith("http")) {
				filename = args[0];
			}
		}
		return filename;
	}

	public static final List<LocalDate> getMissingDates(GeneradorResp resp) {

		List<LocalDate> missingDates = new ArrayList<>();
		LocalDate date = resp.getFechaCreacion().plusDays(0);
		LocalDate finishDate = resp.getFechaFin();

		while (!date.isAfter(finishDate)) {
			if (!resp.getFechas().contains(date)) {
				missingDates.add(date);
			}
			date = date.plusMonths(1);
		}

		return missingDates;

	}

}
