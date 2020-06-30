package view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {
	public static void main(String[] args) 
	{
		IArquivosController ArquivosCon = new ArquivosController();
		String arquivo = "C:\\TEMP\\Cadastro.csv";
		int opc = -1;
		while (opc != 0) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Insira uma op��o: \n 1 - Verificar se o diret�rio existe\n2 - Verificar registro\n3 - Mostrar cadastro\n4 - Inserir cadastro\n0 - Sair"));
			switch(opc) {
			case 1: try {
					ArquivosCon.verificaDirTemp();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2: try {
					if (ArquivosCon.verificaRegistro(arquivo, Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo para pesquisar")))) {
						System.out.println("O c�digo est� cadastrado no registro");
					}else {
						System.out.println("O c�digo n�o foi encontrado no registro");
					}
				} catch (NumberFormatException | HeadlessException | IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					ArquivosCon.imprimeCadastro(arquivo, Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo para imprimir")));
				} catch (NumberFormatException | HeadlessException | IOException e) {
					e.printStackTrace();
				}				
				break;
			case 4: try {
					ArquivosCon.insereCadastro(arquivo, Integer.parseInt(JOptionPane.showInputDialog("Insira o c�digo a ser inserido")), JOptionPane.showInputDialog("Digite o nome a ser inserido"), JOptionPane.showInputDialog("Digite o email a ser inserido"));
				} catch (NumberFormatException | HeadlessException | IOException e) {
					e.printStackTrace();
				}
				break;
			case 0: JOptionPane.showMessageDialog(null, "Encerrando");
				break;
				default: JOptionPane.showMessageDialog(null, "Valor digitado inv�lido");
			}
			}
}
}
