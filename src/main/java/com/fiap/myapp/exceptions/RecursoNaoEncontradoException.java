package com.fiap.myapp.exceptions;

/**
 * Exceção personalizada para recursos não encontrados.
 * Deve ser lançada quando um recurso específico não for localizado.
 */
public class RecursoNaoEncontradoException extends RuntimeException {
    
    /**
     * Construtor padrão com mensagem genérica.
     */
    public RecursoNaoEncontradoException() {
        super("Recurso não encontrado");
    }
    
    /**
     * Construtor com mensagem personalizada.
     * 
     * @param mensagem Descrição detalhada do recurso não encontrado
     */
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    /**
     * Construtor com mensagem e causa da exceção.
     * 
     * @param mensagem Descrição detalhada do recurso não encontrado
     * @param causa Exceção original que motivou este erro
     */
    public RecursoNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
    /**
     * Construtor que recebe a causa da exceção.
     * 
     * @param causa Exceção original que motivou este erro
     */
    public RecursoNaoEncontradoException(Throwable causa) {
        super(causa);
    }
}
