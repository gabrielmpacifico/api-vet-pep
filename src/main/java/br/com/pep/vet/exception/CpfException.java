package br.com.pep.vet.exception;

public class CpfException extends RuntimeException {
    
    public CpfException(String mensagem){
        super(mensagem);
    }

    public static CpfException padrao(String mensagem){
        return new CpfException("Cpf fora do padrão: " + mensagem);
    }
}
