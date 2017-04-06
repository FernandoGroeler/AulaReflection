package br.univel;

import java.lang.reflect.Field;

public class UtilSql {
	public String getSql(Object o) {
		if (!o.getClass().isAnnotationPresent(Tabela.class)) {
			throw new RuntimeException("Classe não anotada.");
		}
		
		if (o.getClass().isAnnotationPresent(Tabela.class)) {
			Tabela tab = o.getClass().getAnnotation(Tabela.class);
			System.out.println(tab.value());
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO ");
		sb.append(getNomeTabela2(o));
		sb.append(" (");
		sb.append(getAtributos(o));
		sb.append(") VALUES (");
		sb.append(getPar(o));
		sb.append(");");		
		
		return sb.toString();
	}
	
	private Object getNomeTabela2(Object o) {
		if (o.getClass().isAnnotationPresent(Tabela.class)) {
			Tabela tab = o.getClass().getAnnotation(Tabela.class);
			
			if (!tab.value().equals("_notset"))
				return tab.value();
		}
		
		Class<?> cl = o.getClass();
		return cl.getSimpleName().toUpperCase();
	}	

	private Object getNomeTabela(Object o) {
		if (o.getClass().isAnnotationPresent(Tabela.class)) {
			Tabela tab = o.getClass().getAnnotation(Tabela.class);
			
			if (tab.value().equals("_notset")) {
				Class<?> cl = o.getClass();
				return cl.getSimpleName().toUpperCase();				
			} else {
				return tab.value();
			}
		} else {
			Class<?> cl = o.getClass();
			return cl.getSimpleName().toUpperCase();
		}
	}

	private Object getAtributos(Object o) {
		StringBuilder sb = new StringBuilder();
		
		int cont = 0;
		
		for (Field f : o.getClass().getDeclaredFields()) {
			if (cont > 0)
				sb.append(", ");
			
			sb.append(f.getName().toUpperCase());
			cont++;
			
		}
		
		return sb.toString();
	}

	private Object getPar(Object o) {
		StringBuilder sb = new StringBuilder();
		
		int qtd = o.getClass().getDeclaredFields().length;
		int cont = 0;

		for (int i = 0; i < qtd; i++) {
			if (cont > 0)
				sb.append(", ");
			
			sb.append("?");
			cont++;
		}
		
		return sb.toString();
	}
}
