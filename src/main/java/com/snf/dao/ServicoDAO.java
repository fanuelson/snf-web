package com.snf.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.snf.builder.JPQLBuilder;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.vo.ServicoDataValorVO;

@SuppressWarnings("unchecked")
public class ServicoDAO extends GenericDAO<Servico, Long> {

	private static final long serialVersionUID = 8644808566924847383L;

	public List<Servico> getServicosByPeriodoAndFuncionario(Date dataInicio, Date dataFinal, Funcionario funcionario) {
		
		List<Servico> servicos = null;

		try {
			getManager().clear();
			JPQLBuilder queryBuilder = new JPQLBuilder()
					.select("s")
					.from(Servico.class, "s")
					.where("(:dataInicio IS NULL OR  s.data>= :dataInicio)", dataInicio)
					.and("(:dataFinal IS NULL OR s.data<= :dataFinal)", dataFinal)
					.and("(:func IS NULL OR s.funcionario = :func)", funcionario);
			
			Query query = getManager().createQuery(queryBuilder.contruir());
			colocarParametros(query, queryBuilder);
			System.out.println(queryBuilder.contruir());
			
			servicos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicos;
	}

	public List<ServicoDataValorVO> servicosByPeriodoAndFuncionario(Date dataInicial, Date dataFinal, Funcionario funcionario) {
		List<ServicoDataValorVO> servicosVO = null;
		
		try {
			getManager().clear();
			TypedQuery<ServicoDataValorVO> query = getManager().createQuery(
					"SELECT new com.snf.vo.ServicoDataValorVO(s.data,SUM(s.valor)) FROM Servico s WHERE DATE(s.data) >= :dataInicial AND DATE(s.data) <= :dataFinal AND (s.funcionario = :Func OR null = :Func ) GROUP BY extract(day from s.data) ORDER BY s.data ASC",ServicoDataValorVO.class);
			query.setParameter("dataInicial", dataInicial);
			query.setParameter("dataFinal", dataFinal);
			query.setParameter("Func", funcionario);
			servicosVO = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return servicosVO;
	}

}
