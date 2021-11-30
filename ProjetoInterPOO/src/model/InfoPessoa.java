package model;

import java.util.Objects;

public class InfoPessoa {

	private String nome;

	private String sexo;

	private double acuraciaMasc;

	private double acuraciaFem;

	public InfoPessoa(String nome, String sexo, double acuraciaMasc, double acuraciaFem) {
		this.nome = nome;
		this.sexo = sexo;
		this.acuraciaMasc = acuraciaMasc;
		this.acuraciaFem = acuraciaFem;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getAcuraciaMasc() {
		return acuraciaMasc;
	}

	public void setAcuraciaMasc(double acuraciaMasc) {
		this.acuraciaMasc = acuraciaMasc;
	}

	public double getAcuraciaFem() {
		return acuraciaFem;
	}

	public void setAcuraciaFem(double acuraciaFem) {
		this.acuraciaFem = acuraciaFem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acuraciaFem, acuraciaMasc, nome, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoPessoa other = (InfoPessoa) obj;
		return Double.doubleToLongBits(acuraciaFem) == Double.doubleToLongBits(other.acuraciaFem)
				&& Double.doubleToLongBits(acuraciaMasc) == Double.doubleToLongBits(other.acuraciaMasc)
				&& Objects.equals(nome, other.nome) && Objects.equals(sexo, other.sexo);
	}
	
	

}
