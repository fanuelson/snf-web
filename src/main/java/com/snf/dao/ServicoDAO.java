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
					.and("(:func = null OR s.funcionario = :func)", funcionario);
			
			Query query = getManager().createQuery(queryBuilder.contruir());
			colocarParametros(query, queryBuilder);
			
			servicos = query.getResultList();
		} catch (Exception e) {
			log.error(e.toString());
		}
		return servicos;
	}

	public List<ServicoDataValorVO> servicosByPeriodoAndFuncionario(Date dataInicial, Date dataFinal, Funcionario funcionario) {
		List<ServicoDataValorVO> servicosVO = null;
		
		try {
			getManager().clear();
			JPQLBuilder queryBuilder = new JPQLBuilder()
					.select("new com.snf.vo.ServicoDataValorVO(s.data,SUM(s.valor))")
					.from(Servico.class, "s")
					.where("(:dataInicial=null OR DATE(s.data) >= :dataInicial)", dataInicial)
					.and("(:dataFinal=null OR DATE(s.data) <= :dataFinal)", dataFinal)
					.and("(s.funcionario = :Func OR null = :Func )", funcionario)
					.groupBy("extract(day from s.data)")
					.orderBy("s.data")
					.asc();
			
			TypedQuery<ServicoDataValorVO> query = getManager().createQuery(queryBuilder.contruir(),ServicoDataValorVO.class);
			
			colocarParametros(query, queryBuilder);
			servicosVO = query.getResultList();
		} catch (Exception e) {
			log.error(e.toString());
		}

		return servicosVO;
	}

}
