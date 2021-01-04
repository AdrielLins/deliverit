package com.teste.deliverIt.resources;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.deliverIt.repository.ContaRepository;
import com.teste.deliverIt.repository.MultaRepository;
import com.teste.deliverIt.model.Conta;
import com.teste.deliverIt.model.Multa;

@RestController
@RequestMapping(value="/api")
public class ContaResource {
	@Autowired
	ContaRepository contaRepository;
	@Autowired
	MultaRepository multaRepository;
	
	@GetMapping("/contas")
	public List<Conta> listaContas(){
		return contaRepository.findAll();
	}
	
	@PostMapping("/conta")
	public Conta newConta(@Valid @RequestBody Conta pConta) {
		Date vencimento = pConta.getDataVencimento();
		Date pagamento = pConta.getDataPagamento();
		
		//se a conta já venceu, feito tratamento para multa
		if(pagamento.after(vencimento)) {
			Integer daysBetween =   Math.toIntExact(ChronoUnit.DAYS.between(convertToLocalDate(pagamento), 
					convertToLocalDate(vencimento)));
			if(daysBetween < 0) {
				Double valorCorrigido = correctsValue(Math.abs(daysBetween),pConta.getValorOriginal());
				pConta.setValorCorrigido(valorCorrigido);
				pConta.setDiasAtraso(Math.abs(daysBetween));
				
			}
			
		}
		return contaRepository.save(pConta);
	}
	
	public LocalDate convertToLocalDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}
	

	public Double correctsValue(Integer diasAtraso, Double valorOriginal) {
		
		Double valorCorrigido = 0.0;
		if(valorOriginal == null) {
			return null;
		}
		if(diasAtraso == null || diasAtraso == 0) {
			return valorOriginal;
		}
		valorCorrigido = valorOriginal;
		List<Multa> multa = null;
		Double multaValue = 1.0;
		Double jurosDia = 1.0;
		//Conforme dias de atraso, retorna do repositório a regra da multa
		if(diasAtraso <= 3) {
			multa = multaRepository.findPercentageRule("baixo");
		} else if (diasAtraso <= 5) {
			multa = multaRepository.findPercentageRule("medio");
		} else if (diasAtraso > 5) {
			multa = multaRepository.findPercentageRule("alto");
		}
		if(multa.isEmpty()){
			return null;
		}
		multaValue = multa.get(0).getMulta();
		jurosDia = multa.get(0).getJurosDia();
		
		//adiciona porcentagem de multa inicial
		valorCorrigido = valorCorrigido + ((valorOriginal * multaValue) / 100);
		
		//adiciona multa diária ao valor
		for (int i = 0; i < diasAtraso; i++) {
			valorCorrigido += (valorCorrigido * jurosDia) / 100;
		}
		
		return valorCorrigido;
		
	}
	
}
