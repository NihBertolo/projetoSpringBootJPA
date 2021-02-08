package com.nicholasbertolo.app.entities.enums;

public enum PedidoStatus {

	AGUARDANDO_PAGAMENTO (1),
	PAGO (2),
	ENCAMINHADO (3),
	EM_TRANSITO (4),
	ENTREGUE (5);
	
	private int code;

	private PedidoStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static PedidoStatus valueOf(int code) {
		for (PedidoStatus value : PedidoStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código invalido");
	}
}

