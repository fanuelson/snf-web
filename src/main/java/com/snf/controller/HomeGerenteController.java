package com.snf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.snf.model.Caixa;
import com.snf.service.CaixaService;
import com.snf.util.MessagesUtils;
import com.snf.vm.AberturaCaixaVM;

@Named
@ViewScoped
public class HomeGerenteController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	final static Logger log = Logger.getLogger(HomeGerenteController.class);

	@Inject
	private CaixaService caixaService;
	
	@Inject
	private AberturaCaixaVM aberturaCaixaVM;
	
	@PostConstruct
	public void init() {
		if(caixaService.getCaixaAberto()!=null){
			aberturaCaixaVM.setCaixa(caixaService.getCaixaAberto());
			aberturaCaixaVM.setExisteCaixaAberto(true);
		} else {
			aberturaCaixaVM.setExisteCaixaAberto(false);
		}
		
	}
	
	public void salvarCaixa() {
		try{
			Caixa caixa = aberturaCaixaVM.getCaixa();
			caixa = caixaService.abrirCaixa(caixa);
			aberturaCaixaVM.setCaixa(caixa);
			aberturaCaixaVM.setExisteCaixaAberto(true);
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		} catch (Exception e){
			log.error(e.toString());
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}
	
	public void fecharCaixa() {
		try{
			caixaService.fecharCaixa(aberturaCaixaVM.getCaixa());
			aberturaCaixaVM.setExisteCaixaAberto(false);
			aberturaCaixaVM.setCaixa(new Caixa());
			MessagesUtils.exibirMensagemSucesso("mensagem.sucesso.salvar.registro");
		} catch (Exception e){
			e.printStackTrace();
			MessagesUtils.exibirMensagemErro("mensagem.erro.salvar.registro");
		}
	}

	public AberturaCaixaVM getAberturaCaixaVM() {
		return aberturaCaixaVM;
	}

	public void setAberturaCaixaVM(AberturaCaixaVM aberturaCaixaVM) {
		this.aberturaCaixaVM = aberturaCaixaVM;
	}

}
