package services;

import java.util.ArrayList;
import java.util.List;

import model.InfoPessoa;

public class InfoPessoaService {

	private static List<InfoPessoa> listInfo = new ArrayList<>();

	public List<InfoPessoa> mostrarDados() {

		return InfoPessoaService.listInfo;

	}

	public void adcionarLista(ArrayList<InfoPessoa> dados) {
		boolean flag;
		
		for (InfoPessoa p : dados) {
			flag = false;
			for (InfoPessoa j : InfoPessoaService.listInfo) {
				if (j.equals(p)) {
					flag = true;
				}
			}
			if (!flag) {
				InfoPessoaService.listInfo.add(p);
			}
		}
	}

}
