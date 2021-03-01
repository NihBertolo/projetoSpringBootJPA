package com.nicholasbertolo.app.entities.enums;

public enum EstadoEnum {
	AC (1, "Acre"),
 	AL (2, "Alagoas"),
 	AP (3, "Amapá"),
 	AM (4, "Amazonas"),
 	BA (5, "Bahia"),
 	CE (6, "Ceará"),
 	ES (7, "Espirito Santo"),
 	GO (8, "Goiás"),
 	MA (9, "Maranhão"),
 	MT (10, "Mato Grosso"),
 	MS (11, "Mato Grosso do Sul"),
 	MG (12, "Minas Gerais"),
 	PA (13, "Pará"),
 	PB (14, "Paraíba"),
 	PR (15, "Paraná"),
 	PE (16, "Pernambuco"),
 	PI (17, "Piauí"),
 	RJ (18, "Rio de Janeiro"),
 	RN (19, "Rio Grande do Norte"),
 	RS (20, "Rio Grande do Sul"),
 	RO (21, "Rondônia"),
 	RR (22, "Roraíma"),
 	SC (23, "Santa Catarina"),
 	SP (24, "São Paulo"),
 	SE (25, "Sergipe"),
 	TO (26, "Tocantins"),
 	DF (27, "Distrito Federal");
 	
 	private int code;
 	private String nome;
 	
 	private EstadoEnum(int code, String nome) {
 		this.code = code;
 		this.nome = nome;
 	}

	public int getCode() {
		return code;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static EstadoEnum valueOf(int code) {
		for (EstadoEnum estado : EstadoEnum.values()) {
			if (estado.getCode() == code) {
				return estado;
			}
		}
		throw new IllegalArgumentException("Código Inválido");
	}
 	
}
