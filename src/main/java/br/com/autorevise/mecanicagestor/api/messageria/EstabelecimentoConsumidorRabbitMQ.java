package br.com.autorevise.mecanicagestor.api.messageria;

import br.com.autorevise.mecanicagestor.api.services.EstabelecimentoService;
import br.com.autorevise.mecanicagestor.api.web.request.EstabelecimentoRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoConsumidorRabbitMQ {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @RabbitListener(queues = "${rabbitmq.queue.criar-oficina}")
    public void criarEstabelecimentoListener(EstabelecimentoRequest request) throws Exception {
        System.out.printf("**** recebendo dados do estabelecimento %s", request.razao());
        try {
            var estabelecimento = estabelecimentoService.cadastrarEstabelecimento(request);
            System.out.printf("*** Estabelecimento '%s' registrado com sucesso!%n", estabelecimento.getRazaoSocial());
        } catch (IllegalStateException e) {
            System.out.printf("**** %s Ignorando...%n", e.getMessage());
        }
    }
}
