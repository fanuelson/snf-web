package com.snf.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.snf.builder.JPQLBuilder;
import com.snf.genericDao.GenericDAO;
import com.snf.model.Funcionario;
import com.snf.model.Servico;
import com.snf.util.DataUtil;
import com.snf.vo.ConsultaServicoVO;
import com.snf.vo.ServicoDataValorVO;

@SuppressWarnings("unchecked")
public class ServicoDAO extends GenericDAO<Servico, Long> {

	private static final long serialVersionUID = 8644808566924847383L;

	public List<Servico> getServicosByPeriodoAndFuncionario(ConsultaServicoVO filtro) {
		List<Servico> servicos = null;
		try {
			
			getManager().clear();
			servicos = new JPQLBuilder()
					.select("s")
					.from(Servico.class, "s")
					.where("(:dataInicial IS NULL OR  s.dataInicio >= :dataInicial)", DataUtil.getDataHoraZerada(filtro.getDataInicial()))
					.and("(:dataFinal IS NULL OR s.dataInicio <= :dataFinal)", DataUtil.getDataHoraFinalDia(filtro.getDataFinal()))
					.and("(:func = null OR s.funcionario = :func)", filtro.getFuncionario())
					.contruir(getManager())
					.getResultList();

		} catch (Exception e) {
			log.error(e.toString());
		}
		
		return servicos;
	}

	public List<ServicoDataValorVO> servicosByPeriodoAndFuncionario(Date dataInicial, Date dataFinal,
			Funcionario funcionario) {
		List<ServicoDataValorVO> servicosVO = new ArrayList<>();

		try {
			getManager().clear();
			servicosVO = new JPQLBuilder()
					.select("new com.snf.vo.ServicoDataValorVO(s.dataInicio ,SUM(s.valor))")
					.from(Servico.class, "s")
					.where("(:dataInicial=null OR DATE(s.dataInicio) >= :dataInicial)", dataInicial)
					.and("(:dataFinal=null OR DATE(s.dataInicio) <= :dataFinal)", dataFinal)
					.and("(s.funcionario = :Func OR null = :Func )", funcionario)
					.groupBy("extract(day from s.dataInicio)")
					.orderBy("s.dataInicio").asc()
					.contruir(getManager(), ServicoDataValorVO.class)
					.getResultList();

		} catch (Exception e) {
			log.error(e.toString());
		}

		return servicosVO;
	}

}
