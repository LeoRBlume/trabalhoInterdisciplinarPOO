package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.InfoPessoa;
import model.Pessoa;

public class ExternalProgram {

	public static void abrirPrograma(ArrayList<Pessoa> cList) throws IOException {

		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "python C:\\Users\\%USERNAME%\\Naive.py "
				+ cList.get(0).getNome() + " " + cList.get(1).getNome() + " " + cList.get(2).getNome());

		pb.start();

	}

	public static ArrayList<InfoPessoa> leituraDoArquivo(ArrayList<Pessoa> cList) {

		String username = System.getProperty("user.name");

		String path = "C:\\Users\\" + username + "\\resultNaive.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			ArrayList<InfoPessoa> listaDados = new ArrayList<InfoPessoa>();

			String line = br.readLine();
			String[] dados;
			int i = 0;

			while (line != null) {
				dados = line.split(" ");
				listaDados.add(new InfoPessoa(cList.get(i).getNome(), dados[0], Double.parseDouble(dados[1]),
						Double.parseDouble(dados[2])));
				line = br.readLine();
				i++;
			}

			return listaDados;

		} catch (Exception e) {

			return null;
			
		}
	}

}
