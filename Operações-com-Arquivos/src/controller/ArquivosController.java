package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController{

	@Override
	public void verificaDirTemp() throws IOException {
		File dir = new File("C:\\TEMP");
		File arq = new File("C:\\TEMP\\Cadastro.csv");
		if (!dir.exists() & dir.isDirectory()) {
		dir.mkdir();
		System.out.println("O diretório foi criado");
		}else {
			System.out.println("O diretório já existe");
			if (!arq.exists()) {
				FileWriter fileWriter = new FileWriter(arq, false);
				fileWriter.close();
				System.out.println("Arquivo criado");
			}else {
				System.out.println("O arquivo já existe");
			}
		}
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File(arquivo);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {			
			if (linha.contains(Integer.toString(codigo))) {
				buffer.close();
				leitor.close();
				fluxo.close();
				return true;
			}
			linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			}else {
				throw new IOException("Arquivo Inválido");
			}
		return false;
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		if (!verificaRegistro(arquivo, codigo)) {
			System.out.println("Código não encontrado no registro");
		}else {
			File arq = new File(arquivo);
			if(arq.exists() && arq.isFile()) {
				FileInputStream fluxo = new FileInputStream(arq);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) { 
					if(linha.contains(Integer.toString(codigo))) {
						String [] strLinha = new String [3];
						strLinha = linha.split(";");
						System.out.println("Código: " + strLinha[0] + "\nNome: " + strLinha[1] + "\nEmail: " + strLinha[2]); 
						break;
						}
					linha = buffer.readLine();
					}
				buffer.close();
				fluxo.close();
				leitor.close();
			}else {
			throw new IOException("Arquivo inválido");
			}
		}
	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		File arq = new File(arquivo);
		if (!verificaRegistro(arquivo, codigo)) {
			String cont = ("\r\n" + Integer.toString(codigo)+ ";" + nome+ ";" +email+ "\r\n");
			FileWriter fileWriter = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(cont);
			print.flush();
			print.close();
			fileWriter.close();
		}else {
			System.out.println("A pessoa já se encontra no registro");
		}
	}

}
