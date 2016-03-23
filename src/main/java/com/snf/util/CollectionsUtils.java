package com.snf.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionsUtils {

	private CollectionsUtils() {
	}

	public static <T> boolean isNullOrEmpty(Collection<T> colecao) {
		if (colecao == null)
			return true;
		if (colecao.isEmpty())
			return true;

		return false;

	}

	public static <T> List<T> castToList(Collection<T> colecao) {
		List<T> lista = new ArrayList<>();
		if (!isNullOrEmpty(colecao)) {
			if (colecao instanceof List) {
				lista = (List<T>) colecao;
			} else {
				lista = new ArrayList<>(colecao);
			}
		}
		return lista;

	}
}
