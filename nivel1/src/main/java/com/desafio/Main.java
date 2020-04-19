package com.desafio;

import com.desafio.models.InputModel;
import com.desafio.models.OutputModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(final String[] args) {
		if (args.length >= 2){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

			try{
				//args[0] inputfile
				InputModel input = gson.fromJson(new FileReader(args[0]), InputModel.class);
				OutputModel output = MissingDates.find(input);
				//args[1] outputfile
				FileWriter fw = new FileWriter(args[1]);
				fw.write(gson.toJson(output));
				fw.flush();
				fw.close();
				System.out.println(args[1] + " writed");
			}catch ( FileNotFoundException ex){
				System.out.println("input file not found " + ex.getMessage());
			}catch ( ParseException ex){
				System.out.println("parsing dates fail");
			}catch ( IOException ex) {
				System.out.println("erro handling file system");
			}
		}
		else{
			System.out.println("Faltan parametros ... inputFile.json outputFile.json");
		}
	}
}
