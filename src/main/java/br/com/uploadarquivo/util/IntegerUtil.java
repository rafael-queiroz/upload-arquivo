package br.com.uploadarquivo.util;

import org.springframework.stereotype.Component;

@Component
public class IntegerUtil {

	boolean ehInteiro(String s) {
		char[] c = s.toCharArray();
		boolean d = true;
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				d = false;
				break;
			}
		}
		return d;
	}
}
