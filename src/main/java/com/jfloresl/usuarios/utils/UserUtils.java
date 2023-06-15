package com.jfloresl.usuarios.utils;

import org.springframework.stereotype.Service;

@Service
public class UserUtils {
	public static boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
	}
}
