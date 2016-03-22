package teste;

import java.util.Date;
import java.util.Map;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Servico;

public class TesteCaixa {

	public static void main(String[] args) {
		
		String q = "SELECT s FROM Servico s WHERE (:dataInicio IS NULL OR  s.data>= :dataInicio) AND (:dataFinal IS NULL OR s.data<= :dataFinal) AND (:func IS NULL OR s.funcionario = :func)";
		JPQLBuilder queryBuilder = new JPQLBuilder()
				.select("s")
				.from(Servico.class, "s")
				.where("(:dataInicio IS NULL OR  s.data>= :dataInicio)", 2)
				.and("(:dataFinal IS NULL OR s.data<= :dataFinal)", 2)
				.and("(:func IS NULL OR s.funcionario = :func)", 2);
		
		for(Map.Entry<String, Object> parametro : queryBuilder.getParametros().entrySet()) {
			System.out.println(parametro.getKey()+" -- "+ parametro.getValue());
		}
		
		System.out.println(queryBuilder.contruir());
	}

}
