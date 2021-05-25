package cl.previred.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.previred.constants.AppConstants;

public class AppUtils {

	public static List<String> getDatesString(List<Date> fechas) {

		List<String> fechasSt = new ArrayList<>();

		for (Date date : fechas) {
			fechasSt.add(AppUtils.dateFormatFactory(DateFormatEnum.FORMATO_ESTANDAR).format(date));
		}

		return fechasSt;
	}

	public static String getDatesString(Date fecha) {


		return AppUtils.dateFormatFactory(DateFormatEnum.FORMATO_ESTANDAR).format(fecha);
	}

	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static SimpleDateFormat dateFormatFactory(DateFormatEnum formatoFechaEnum) {

		return new SimpleDateFormat(formatoFechaEnum.getPattern());

	}


	public static List<Date> createFullList(Date createDate,Date endDate)
			throws ParseException {

		List<Date> totalDates = new ArrayList<>();
		
		Calendar calendar = Calendar.getInstance();
	
		calendar.setTime(createDate);
		Integer createYear = calendar.get(Calendar.YEAR);
		Integer createMonth = calendar.get(Calendar.MONTH) + 1;

		calendar.setTime(endDate);
		Integer endYear = calendar.get(Calendar.YEAR);
		Integer endMonth = calendar.get(Calendar.MONTH) + 1;
		

		Integer initialMonth;
		Integer finalMonth;

		for (int anioCont = createYear; anioCont <= endYear; anioCont++) {

			// Ajuste cuando el rango no sea preciso desde el primer mes
			if (anioCont == createYear) {
				initialMonth = createMonth;
			} else {
				initialMonth = AppConstants.PRIMER_MES;
			}

			if (anioCont == endYear) {
				finalMonth = endMonth;
			} else {
				finalMonth = AppConstants.ULTIMO_MES;
			}

			for (Integer mesCont = initialMonth; mesCont <= finalMonth; mesCont++) {

				String mes = mesCont.toString();
				if (mes.length() == 1) {
					mes = AppConstants.PREFIJO_CERO + mes;
				}
				totalDates.add(AppUtils.dateFormatFactory(DateFormatEnum.FORMATO_ESTANDAR)
						.parse(anioCont + "-" + mes + "-" + AppConstants.PRIMER_DIA_MES));
			}

		}

		return totalDates;
	}
}
