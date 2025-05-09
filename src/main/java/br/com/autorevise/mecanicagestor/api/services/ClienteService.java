package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Cliente;
import br.com.msoficinas.api.web.request.AddVeiculoClienteRequest;
import br.com.msoficinas.api.web.request.ClienteAddRequest;
import br.com.msoficinas.api.web.response.ClienteResponse;
import br.com.msoficinas.api.web.response.FormClienteResponse;

public interface ClienteService {

    Cliente cadastrarCliente(ClienteAddRequest request) throws Exception;

    FormClienteResponse buscaClienteParaEstabelecimentoPeloCpfOuCnpj(String cpfOuCnpj) throws Exception;

    Cliente adicionarVeiculoParaCliente(AddVeiculoClienteRequest request) throws Exception;

    ClienteResponse buscaClienteParaEstabelecimentoPeloId(String idCliente) throws Exception;

    Cliente buscarClientePeloId(String idCliente) throws Exception;
}
