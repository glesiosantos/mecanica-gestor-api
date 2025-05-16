package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Cliente;
import br.com.autorevise.mecanicagestor.api.web.request.AddVeiculoClienteRequest;
import br.com.autorevise.mecanicagestor.api.web.request.ClienteAddRequest;
import br.com.autorevise.mecanicagestor.api.web.response.ClienteResponse;
import br.com.autorevise.mecanicagestor.api.web.response.FormClienteResponse;

public interface ClienteService {

    Cliente cadastrarCliente(ClienteAddRequest request) throws Exception;

    FormClienteResponse buscaClienteParaEstabelecimentoPeloCpfOuCnpj(String cpfOuCnpj) throws Exception;

    Cliente adicionarVeiculoParaCliente(AddVeiculoClienteRequest request) throws Exception;

    ClienteResponse buscaClienteParaEstabelecimentoPeloId(String idCliente) throws Exception;

    Cliente buscarClientePeloCpfOuCnpj(String cpfCnpj);

    Cliente buscarClientePeloId(String idCliente) throws Exception;
}
