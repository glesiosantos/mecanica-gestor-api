package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrdemStatus {

    AA("Aguardando Autorização", "Orçamento enviado, aguardando o cliente autorizar", TipoDeProposta.O),
//    AD("Aguardando autorização adicional", "se surgir um novo problema no meio do serviço", TipoDeProposta.O),
    AU("Autorizado", "Cliente deu o 'ok', serviço aprovado para execução", TipoDeProposta.P),
    AP("Aguardando Pagamento","Serviço feito, mas pagamento ainda não confirmado", TipoDeProposta.P),
    CA("Cancelado", "Serviço cancelado antes ou durante a execução", TipoDeProposta.P),
//    EA("Em análise técnica", "quando o diagnóstico ainda está sendo feito", TipoDeProposta.P),
    EE("Em Execução","Serviço já iniciado pela oficina", TipoDeProposta.P),
    PE("Pendente", "Execução pausada por falta de peças ou insumos", TipoDeProposta.P),
//    SE("Serviço Concluído", "Serviço finalizado, aguardando retirada ou entrega", TipoDeProposta.P),
    NA("Não autorizado","Orçamento foi negado ou passou do prazo da solicitação", TipoDeProposta.O),
    FI("Finalizado","Serviço e pagamento concluídos, veículo liberado", TipoDeProposta.P);
//    RT("Revisão final / Testes","testes de qualidade pós-serviço", TipoDeProposta.P);

    private final String descricao;
    private final String contexto;
    private final TipoDeProposta proposta;
}
