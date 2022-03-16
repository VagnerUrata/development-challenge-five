package com.vagnerurata.domain.enums;

public enum EPerfil {

	ADMIN(0, "ROLE_ADMIN");

	private Integer code;

	private String description;

	private EPerfil(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static EPerfil toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (EPerfil perfil : EPerfil.values()) {

			if (cod.equals(perfil.getCode())) {
				return perfil;
			}
		}

		throw new IllegalArgumentException("Perfil inválido");
	}
}