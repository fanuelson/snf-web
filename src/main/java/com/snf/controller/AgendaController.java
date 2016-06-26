package com.snf.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.ScheduleEvent;

import com.snf.builder.ServicoBuilder;
import com.snf.model.Servico;
import com.snf.service.CaixaService;
import com.snf.service.FuncionarioService;
import com.snf.service.ServicoService;
import com.snf.util.DataUtil;
import com.snf.util.MessagesUtils;
import com.snf.vm.AgendaVM;

@Named
@ViewScoped
public class AgendaController implements Serializable {

	private static final String SCRIPT_ESCONDER_VALOR_DIALOG = "PF('valorDialog').hide()";

	private static final String SCRIPT_ESCONDER_SERVICO_DIALOG = "PF('servicoDialog').hide()";

	private static final String mensagem_erro = "mensagem.erro.salvar.registro";

	private static final String mensagem_sucesso = "mensagem.sucesso.salvar.registro";

	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(AgendaController.class);

	@Inject
	private ServicoService servicoService;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private CaixaService caixaService;

	@Inject
	private AgendaVM agendaVM;

	@PostConstruct
	public void init() {
		agendaVM.setFuncionarios(funcionarioService.getAll());
		agendaVM.setNaoExisteCaixaAberto(!caixaService.existeCaixaAberto());
	}

	public void onDateSelect(SelectEvent event) {
		Date dataInicio = (Date) event.getObject();
		Date dataFim = new DateTime(dataInicio).plusMinutes(30).toDate();
		Servico servico = new ServicoBuilder().comDataInicio(dataInicio).comDataFim(dataFim).contruir();
		agendaVM.setServicoSelected(servico);
	}

	public void onServicoSelect(SelectEvent selectEvent) {
		Servico servico = (Servico) selectEvent.getObject();
		agendaVM.setServicoSelected(servico);
	}

	public void onServicoMove(ScheduleEntryMoveEvent event) {
		Servico servico = (Servico) event.getScheduleEvent();
		servicoService.salvar(servico);
		init();
	}

	public void onServicoResize(ScheduleEntryResizeEvent event) {
		Servico servico = (Servico) event.getScheduleEvent();
		servicoService.salvar(servico);
	}

	public void salvar() {
		try {
			servicoService.salvar(agendaVM.getServicoSelected());
			init();
			MessagesUtils.exibirMensagemSucesso(mensagem_sucesso);
			RequestContext.getCurrentInstance().execute(SCRIPT_ESCONDER_SERVICO_DIALOG);
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro(mensagem_erro);
		}
	}
	
	public void pagar() {
		try {
			servicoService.salvarServicoPago(agendaVM.getServicoSelected());
			init();
			MessagesUtils.exibirMensagemSucesso(mensagem_sucesso);
			limparServicoSelected();
			RequestContext.getCurrentInstance().execute(SCRIPT_ESCONDER_VALOR_DIALOG);
			RequestContext.getCurrentInstance().execute(SCRIPT_ESCONDER_SERVICO_DIALOG);
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro(mensagem_erro);
		}
	}
	
	private void limparServicoSelected() {
		agendaVM.setServicoSelected(new Servico());
	}
	
	public void agendarServico() {
		try {
			if(validarAgendamentoServico()){
				servicoService.agendar(agendaVM.getServicoSelected());
			}else{
				MessagesUtils.exibirMensagemErro("mensagem.erro.servico.colide");
				return;
			}
			init();
			MessagesUtils.exibirMensagemSucesso(mensagem_sucesso);
			RequestContext.getCurrentInstance().execute(SCRIPT_ESCONDER_SERVICO_DIALOG);
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro(mensagem_erro);
		}
	}

	private boolean validarAgendamentoServico(){
		Servico servicoSelecionado = agendaVM.getServicoSelected();
		for (ScheduleEvent evento : agendaVM.getAgendaModel().getEvents()) {
			Servico servico = (Servico) evento;
			if(servico.getFuncionario().equals(servicoSelecionado.getFuncionario())){
				if(datasAgendamentosColidem(servicoSelecionado, servico)){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean datasAgendamentosColidem(Servico servicoSelecionado, Servico servico){
		if(DataUtil.isDentroDoPeriodoFechado(servicoSelecionado.getDataInicio(), servico.getDataInicio(), servico.getDataFim())){
			return true;
		}
		if(DataUtil.isDentroDoPeriodoFechado(servicoSelecionado.getDataFim(), servico.getDataInicio(), servico.getDataFim())){
			return true;
		}
		return false;
	}

	public void cancelarServico() {
		try {
			servicoService.cancelar(agendaVM.getServicoSelected());
			init();
			MessagesUtils.exibirMensagemSucesso(mensagem_sucesso);
			RequestContext.getCurrentInstance().execute(SCRIPT_ESCONDER_SERVICO_DIALOG);
		} catch (Exception e) {
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro(mensagem_erro);
		}
	}
	
	public void atualizarDataFim(SelectEvent event) {
		Date data = (Date) event.getObject();
		Date dataFim = DataUtil.somarMinutos(data, 30);
		agendaVM.getServicoSelected().setDataFim(dataFim);
	}

	public ServicoService getServicoService() {
		return servicoService;
	}

	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	public AgendaVM getAgendaVM() {
		return agendaVM;
	}

	public void setAgendaVM(AgendaVM agendaVM) {
		this.agendaVM = agendaVM;
	}

}
