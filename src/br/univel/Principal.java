package br.univel;

import java.lang.reflect.Field;

public class Principal {
	public Principal() {
		Cliente c1 = new Cliente();
		c1.setId(1);
		c1.setNome("Nome 1");
		
		exibirNomeDaClass(c1);
		exibirNomeAtributos(c1);
		
		try {
			exibirNomeEValoresAtributos(c1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		System.out.println("====================================");
		
		UtilSql utl = new UtilSql();
		String sql = utl.getSql(c1);
		System.out.println(sql);
	}
	
	private void exibirNomeEValoresAtributos(Object c1) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = c1.getClass();
		
		for (Field f : clazz.getDeclaredFields()) {
			f.setAccessible(true);
			
			Object valor = f.get(c1);
			System.out.println(f.getName() + "\t" + valor);
		}
	}

	private void exibirNomeAtributos(Object c1) {
		Class<?> clazz = c1.getClass();
		
		for (Field f : clazz.getDeclaredFields()) {
			System.out.println(f.getName());
		}
	}

	private void exibirNomeDaClass(Object c1) {
		Class<?> clazz = c1.getClass();
		System.out.println(clazz.getName());
	}

	public static void main(String[] args) {
		new Principal();
	}
}
