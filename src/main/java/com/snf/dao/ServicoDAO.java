package com.snf.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.snf.model.Funcionario;
import com.snf.model.Servico;

@SuppressWarnings("unchecked")
public class ServicoDAO extends GenericDAO<Servico, Long> {

	private static final long serialVersionUID = 8644808566924847383L;

	public List<Servico> getServicosByPeriodo(Date dataInicio, Date dataFinal) {
		List<Servico> servicos = null;

		try {
			Query query = getManager().createQuery(
					"SELECT s FROM Servico s WHERE (:dataInicio IS NULL OR s.data>= :dataInicio) AND (:dataFinal IS NULL OR s.data<= :dataFinal)");
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFinal", dataFinal);
			servicos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicos;
	}

	public List<Servico> getServicosByPeriodoAndFuncionario(Date dataInicio, Date dataFinal, Funcionario funcionario) {
		
		List<Servico> servicos = null;
		if (dataInicio != null)
			System.out.println(dataInicio);

		try {
			getManager().clear();
			Query query = getManager().createQuery(
					"SELECT s FROM Servico s WHERE (:dataInicio IS NULL OR  s.data>= :dataInicio) AND (:dataFinal IS NULL OR s.data<= :dataFinal) AND (:func IS NULL OR s.funcionario = :func)");
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFinal", dataFinal);
			query.setParameter("func", funcionario);
			servicos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicos;
	}

	public List<Servico> getServicosByFuncionario(Funcionario funcionario) {
		List<Servico> servicos = null;

		try {
			Query query = getManager().createQuery("SELECT s FROM Servico s WHERE s.funcionario = :func");
			query.setParameter("func", funcionario);
			servicos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicos;
	}

	public List<Object[]> servicosByPeriodoAndFuncionario(Date dataInicial, Date dataFinal, Funcionario funcionario) {
		List<Object[]> pedidos = null;
		Long idFuncionario = 0L;
		if(funcionario!=null)
			 idFuncionario = funcionario.getId();
		
		try {
			getManager().clear();
			Query query = getManager().createNativeQuery(
					"SELECT *,SUM(s.valor) as valorTotal2 FROM Servico s WHERE DATE(s.data) >= :dataInicial AND DATE(s.data) <= :dataFinal AND (:idFunc = 0 || s.idFuncionario = :idFunc ) GROUP BY extract(day from s.data) ORDER BY s.data ASC");
			query.setParameter("dataInicial", dataInicial);
			query.setParameter("dataFinal", dataFinal);
			query.setParameter("idFunc", idFuncionario);
			pedidos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pedidos;
	}

}
