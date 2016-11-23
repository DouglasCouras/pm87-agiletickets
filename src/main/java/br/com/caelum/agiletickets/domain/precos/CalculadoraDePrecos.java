package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		preco = sessao.getPreco();
		
		switch (sessao.getEspetaculo().getTipo()) {
		case CINEMA:
		case SHOW:
			if(sessao.isLimiteDisponivel(0.05)) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
			break;
			
		case BALLET:
		case ORQUESTRA:
			if(sessao.isLimiteDisponivel(0.5)) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			}
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
			break;

		default:
			break;
		}
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}