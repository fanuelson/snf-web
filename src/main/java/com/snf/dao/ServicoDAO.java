package com.snf.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.snf.builder.JPQLBuilder;
import com.snf.genericDao.GenericDAO;
import com.snf.lazyModel.PaginaDataModel;
import com.snf.model.Servico;
import com.snf.util.DataUtil;
import com.snf.vo.FiltroConsultaServicoVO;
import com.snf.vo.RelatorioServicoVO;

@SuppressWarnings("unchecked")
public class ServicoDAO extends GenericDAO<Servico, Long> {

	private static final long serialVersionUID = 8644808566924847383L;
	
	private static final Logger log = Logger.getLogger(ServicoDAO.class);

	public List<Servico> getServicosByPeriodoAndFuncionario(FiltroConsultaServicoVO filtro) {
		List<Servico> servicos = null;
		try {
			
			getManager().clear();
			servicos = new JPQLBuilder()
					.select("s")
					.from(Servico.class, "s")
					.where("(:dataInicial IS NULL OR  s.dataInicio >= :dataInicial)", DataUtil.getDataHoraZerada(filtro.getDataInicial()))
					.and("(:dataFinal IS NULL OR s.dataInicio <= :dataFinal)", DataUtil.getDataHoraFinalDia(filtro.getDataFinal()))
					.and("(:func = null OR s.funcionario.idUsuario = :func)", filtro.getIdFuncionario())
					.contruir(getManager())
					.getResultList();

		} catch (Exception e) {
			log.error(e.toString());
		}
		
		return servicos;
	}
	
	public PaginaDataModel<Servico> getServicosByPeriodoAndFuncionario(FiltroConsultaServicoVO filtro, PaginaDataModel<Servico> pagina) {
		try {
			getManager().clear();
			pagina = new JPQLBuilder()
					.select("s")
					.from(Servico.class, "s")
					.where("(:dataInicial IS NULL OR  s.dataInicio >= :dataInicial)", DataUtil.getDataHoraZerada(filtro.getDataInicial()))
					.and("(:dataFinal IS NULL OR s.dataInicio <= :dataFinal)", DataUtil.getDataHoraFinalDia(filtro.getDataFinal()))
					.and("(:func = null OR s.funcionario.idUsuario = :func)", filtro.getIdFuncionario())
					.contruirPaginado(getManager(), pagina, Servico.class);

		} catch (Exception e) {
			log.error(e.toString());
		}
		
		return pagina;
	}

	public List<RelatorioServicoVO> servicosByPeriodoAndFuncionario(FiltroConsultaServicoVO filtro) {
		List<RelatorioServicoVO> servicosVO = new ArrayList<>();

		try {
			getManager().clear();
			servicosVO = new JPQLBuilder()
					.select("new com.snf.vo.RelatorioServicoVO(s.dataInicio ,SUM(s.valor))")
					.from(Servico.class, "s")
					.where("(:dataInicial=null OR DATE(s.dataInicio) >= :dataInicial)", DataUtil.getDataHoraZerada(filtro.getDataInicial()))
					.and("(:dataFinal=null OR DATE(s.dataInicio) <= :dataFinal)", DataUtil.getDataHoraFinalDia(filtro.getDataFinal()))
					.and("(:func = null OR s.funcionario.idUsuario = :func)", filtro.getIdFuncionario())
					.groupBy("extract(day from s.dataInicio)")
					.orderBy("s.dataInicio").asc()
					.contruir(getManager(), RelatorioServicoVO.class)
					.getResultList();

		} catch (Exception e) {
			log.error(e.toString());
		}

		return servicosVO;
	}
	
	public Double getSomaTotalServicos(FiltroConsultaServicoVO filtro) {
		Double somaTotal = 0.0;
		try {
			getManager().clear();
			somaTotal = new JPQLBuilder()
					.select("new java.lang.Double(SUM(s.valor))")
					.from(Servico.class, "s")
					.where("(:dataInicial=null OR DATE(s.dataInicio) >= :dataInicial)", DataUtil.getDataHoraZerada(filtro.getDataInicial()))
					.and("(:dataFinal=null OR DATE(s.dataInicio) <= :dataFinal)", DataUtil.getDataHoraFinalDia(filtro.getDataFinal()))
					.and("(:func = null OR s.funcionario.idUsuario = :func)", filtro.getIdFuncionario())
					.and("s.valor IS NOT NULL")
					.contruir(getManager(), Double.class)
					.getSingleResult();

		} catch (Exception e) {
			log.error(e.toString());
		}

		return somaTotal;
	}

}
