package com.jfloresl.usuarios.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jfloresl.usuarios.entities.Phone;

@Service
public class UserUtils {
	/**
	 * Funcion que retorna true/false si str esta vacio o es null
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
	}

	/**
	 * Funcion que retorna true/false si la lista de Phone esta vacio o es null
	 * @param phones
	 * @return
	 */
	public static boolean isNullOrEmpty(List<Phone> phones) {
		return phones == null || phones.size()==0;
	}

	/**
	 * Funcion que retorna el primer valor aportado si no es null ni vacio
	 * en caso contrario entrega el segundo valor aportado
	 * @param name
	 * @param name2
	 * @return
	 */
	public static String ifFirstExistReturnFirst(String name, String name2) {
		if(!isNullOrEmpty(name)) {
			return name;
		}
		return name2;
	}
}
